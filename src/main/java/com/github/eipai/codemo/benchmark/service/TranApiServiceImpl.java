package com.github.eipai.codemo.benchmark.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.eipai.codemo.benchmark.bean.RespCode;
import com.github.eipai.codemo.benchmark.bean.TranException;
import com.github.eipai.codemo.benchmark.bean.TranMetaData;
import com.github.eipai.codemo.benchmark.bean.TranRequest;
import com.github.eipai.codemo.benchmark.bean.TranResponse;
import com.github.eipai.codemo.benchmark.bean.TransInfoVo;
import com.github.eipai.codemo.benchmark.utils.Global;

@Service
public class TranApiServiceImpl implements TranApiService {
    private final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private TranAccountService tranAccountService;

    @Override
    public TranResponse tran(TranRequest request) {
        logger.info("RECEIVE TRAN. BEGIN." + request);
        if (null == request) return new TranResponse(null, RespCode.NULL_ARGUMENT);

        long enterTime = System.currentTimeMillis();
        TranResponse response = new TranResponse();
        response.setRespCode(RespCode.SUCCESS);
        response.setId(request.getId());

        TranMetaData tranMetaData = new TranMetaData();
        try {
            if (!checkRequest(request, response, tranMetaData)) return response;
            tranAccountService.saveTransInfo(request, tranMetaData);
            tranAccountService.updateTransInfo(request);
            tranAccountService.doTrans(request, response, tranMetaData);
        } catch (TranException e) {
            logger.error("TRAN EXCEPTION!", e);
            RespCode rc = e.getRespCode();
            response.setRespCode(rc);
            response.setDetailMessage(e.getMessage());
            tranMetaData.getOrderId2TransInfoIds().forEach((oid, tid) -> tranAccountService.updateTransInfoRespCode(tid, rc, false));
        } catch (Exception e) {
            logger.error("TRAN EXCEPTION!", e);
            response.setRespCode(RespCode.FAILURE);
            response.setDetailMessage(e.getMessage());
        }

        logger.info("RECEIVE TRAN.   END.<" + (System.currentTimeMillis() - enterTime) + ">" + response);
        return response;
    }

    private boolean checkRequest(TranRequest request, TranResponse response, TranMetaData tranMetaData) {
        if (null == request || null == request.getTransInfos() || 0 == request.getTransInfos().size()) {
            throw new TranException(RespCode.NULL_ARGUMENT, "TranRequest or TranRequest.transInfos is empty!");
        }

        request.getTransInfos().stream().forEach(transInfo -> checkTransInfo(transInfo, tranMetaData, response));

        if (!tranMetaData.checkDrCrEqual()) {
            throw new TranException(RespCode.CR_DR_NOT_EQUALS, "CR & DR not equals:" + tranMetaData.getUserAcctDiff());
        }
        return true;
    }

    private Object checkTransInfo(TransInfoVo transInfo, TranMetaData tranMetaData, TranResponse response) {
        if (!Global.Utils.checkAmount(transInfo.getAmount())) throw new TranException(RespCode.WRONG_AMOUNT);
        if (StringUtils.isBlank(transInfo.getOrderId())) throw new TranException(RespCode.ORDER_ID_EMPTY);

        boolean singleReceiver = transInfo.getReceiver() > 0;
        boolean multiReceiver = StringUtils.isNotBlank(transInfo.getReceiversInfo());
        //cannot be true or false at the same time
        if (singleReceiver == multiReceiver) throw new TranException(RespCode.TRAN_RECEIVER_ARG_WRONG);

        if (null != tranMetaData.getTranDetails().get(transInfo.getOrderId())) {
            throw new TranException(RespCode.ORDER_ID_DUPLICATE, "orderId duplicate:" + transInfo.getOrderId());
        }

        Long acctId = transInfo.getPayer();
        checkAndAddAcctDiff(tranMetaData.getUserAcctDiff(), acctId, Global.Utils.toNegative(transInfo.getAmount()));
        if (singleReceiver) {
            checkAndAddAcctDiff(tranMetaData.getUserAcctDiff(), transInfo.getReceiver(), transInfo.getAmount());
            tranMetaData.addAcctDetail(transInfo.getOrderId(), acctId, transInfo.getReceiver(), transInfo.getAmount());
        }
        if (multiReceiver) {
            List<ReceiverUnit> recUnits = new ArrayList<ReceiverUnit>();
            parseReceivers(response, transInfo.getReceiversInfo(), recUnits);
            checkReceivers(response, transInfo, recUnits, tranMetaData);
        }
        return null;
    }

    private void checkAndAddAcctDiff(Map<Long, BigDecimal> userAcctDiff, Long acctId, BigDecimal amount) {
        Global.Assert.notNull(acctId, "acctId must not be null.");
        BigDecimal exist = userAcctDiff.get(acctId);
        if (null == exist) {
            exist = new BigDecimal("0");
            userAcctDiff.put(acctId.longValue(), exist);
        }
        userAcctDiff.put(acctId.longValue(), exist.add(amount));
    }

    private boolean checkReceivers(TranResponse response, TransInfoVo transInfo, List<ReceiverUnit> tranUnits, TranMetaData tranMetaData) {
        BigDecimal d = Global.Decimal._10000;
        BigDecimal sum = new BigDecimal(tranUnits.stream().mapToLong(a -> multLong(a.amount, d)).sum()).divide(d);
        if (!sum.equals(transInfo.getAmount())) {
            throw new TranException(RespCode.TRAN_RECEIVERS_AMOUNT_WRONG, "sum=" + sum + ", amount=" + transInfo.getAmount());
        }
        for (ReceiverUnit ru : tranUnits) {
            checkAndAddAcctDiff(tranMetaData.getUserAcctDiff(), ru.acctId, ru.amount);
            tranMetaData.addAcctDetail(transInfo.getOrderId(), transInfo.getPayer(), ru.acctId, ru.amount);
        }
        return true;
    }

    public static long multLong(BigDecimal arg1, BigDecimal arg2) {
        Global.Assert.notNull(arg1);
        Global.Assert.notNull(arg2);
        return arg1.multiply(arg2).longValue();
    }

    private boolean parseReceivers(TranResponse response, String recievers, List<ReceiverUnit> tranUnits) {
        String[] recs = StringUtils.split(recievers, Global.Symbol.PIPE);
        if (null == recs || 0 == recs.length) throw new TranException(RespCode.TRAN_RECEIVERS_EMPTY);

        Arrays.stream(recs).forEach(rec -> {
            String[] r = StringUtils.split(rec, Global.Symbol.COLON);
            if (null == r || 2 != r.length) throw new TranException(RespCode.TRAN_RECEIVERS_WRONG);
            String acctIdStr = r[0];
            String amountStr = r[1];
            Long acctId = Global.Utils.parseLong(acctIdStr, true);
            if (acctId == null) throw new TranException(RespCode.TRAN_RECEIVER_ID_WRONG);
            BigDecimal amount = Global.Utils.parseBigDecimal(amountStr);
            if (amount == null) throw new TranException(RespCode.TRAN_RECEIVER_AMOUNT_WRONG);
            tranUnits.add(new ReceiverUnit(acctId, amount));
        });

        return true;
    }

    class ReceiverUnit {
        long acctId;
        BigDecimal amount;

        public ReceiverUnit() {
            super();
        }

        public ReceiverUnit(long acctId, BigDecimal amount) {
            super();
            this.acctId = acctId;
            this.amount = amount;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("ReceiverUnit [acctId=");
            builder.append(acctId);
            builder.append(", amount=");
            builder.append(amount);
            builder.append("]");
            return builder.toString();
        }
    }
}
