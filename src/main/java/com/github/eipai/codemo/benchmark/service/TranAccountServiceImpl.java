package com.github.eipai.codemo.benchmark.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.eipai.codemo.benchmark.bean.CdType;
import com.github.eipai.codemo.benchmark.bean.DaoException;
import com.github.eipai.codemo.benchmark.bean.RespCode;
import com.github.eipai.codemo.benchmark.bean.TranException;
import com.github.eipai.codemo.benchmark.bean.TranMetaData;
import com.github.eipai.codemo.benchmark.bean.TranRequest;
import com.github.eipai.codemo.benchmark.bean.TranResponse;
import com.github.eipai.codemo.benchmark.bean.TransInfoVo;
import com.github.eipai.codemo.benchmark.domain.AccountDetail;
import com.github.eipai.codemo.benchmark.domain.SeqGenerator.SeqGeneratorCode;
import com.github.eipai.codemo.benchmark.domain.TransInfo;
import com.github.eipai.codemo.benchmark.domain.UserAccount;
import com.github.eipai.codemo.benchmark.utils.Global;

@Service
public class TranAccountServiceImpl extends AbstractService implements TranAccountService {

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void updateTransInfo(TranRequest request) {
        List<TransInfoVo> transInfos = request.getTransInfos();
        if (null == transInfos || 0 == transInfos.size()) return;
        try {
            for (TransInfoVo vo : transInfos) {
                TransInfo transInfo = transInfoManager.get(vo.getId());
                if (null == transInfo) throw new TranException("TransInfo not found. id=" + vo.getId());
                transInfo.setAccountStart(true);
                transInfo.setAcctProcTime(new Date());
                transInfoManager.update(transInfo);
            }
        } catch (DaoException e) {
            throw new TranException(e.getRespCode(), e);
        } catch (Exception e) {
            throw new TranException(RespCode.UNEXPECTED_EXCEPTION, e);
        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void saveTransInfo(TranRequest request, TranMetaData tranMetaData) {
        List<TransInfoVo> transInfos = request.getTransInfos();
        if (null == transInfos || 0 == transInfos.size()) return;
        try {
            for (TransInfoVo vo : transInfos) {
                TransInfo transInfo = new TransInfo();
                long id = seqGeneratorService.getNext(SeqGeneratorCode.tran_info);
                tranMetaData.putOrderId2TransInfoIds(vo.getOrderId(), id);
                vo.setId(id);
                transInfo.setId(vo.getId());
                transInfo.setAmount(vo.getAmount());
                transInfo.setOrderId(vo.getOrderId());
                transInfo.setPayer(vo.getPayer());
                transInfo.setReceiver(vo.getReceiver());
                transInfo.setReceiversInfo(vo.getReceiversInfo());
                transInfo.setRemark(vo.getRemark());
                transInfoManager.insert(transInfo);
            }
        } catch (DaoException e) {
            throw new TranException(e.getRespCode(), e);
        } catch (Exception e) {
            throw new TranException(RespCode.UNEXPECTED_EXCEPTION, e);
        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void doTrans(TranRequest request, TranResponse response, TranMetaData tranMiddleData) {
        try {
            Map<Long, BigDecimal> acctOrigBalance = new HashMap<>();
            for (Map.Entry<Long, BigDecimal> entry : tranMiddleData.getUserAcctDiff().entrySet()) {
                Long acctId = entry.getKey();
                BigDecimal value = entry.getValue();
                UserAccount account = userAccountManager.get(acctId);
                if (!checkAccountStatus(response, acctId, value, account)) return;
                acctOrigBalance.put(acctId, account.getMoney());
                account.setMoney(account.getMoney().add(value));
                userAccountManager.update(account);
            }

            List<TransInfoVo> transInfos = request.getTransInfos();
            if (null == transInfos || 0 == transInfos.size()) return;
            for (TransInfoVo vo : transInfos) {
                String orderId = vo.getOrderId();
                List<AccountDetail> accountDetails = tranMiddleData.getTranDetails().get(orderId);
                if (null == accountDetails || 0 == accountDetails.size()) continue;
                TransInfoVo transInfo = request.getFirstByOrderId(orderId);
                if (null == transInfo) throw new TranException("TransInfo not found. orderId=" + orderId);
                int indx = 1;
                for (AccountDetail accountDetail : accountDetails) {
                    Long acctId = accountDetail.getAcctId();
                    BigDecimal balance = acctOrigBalance.get(acctId);
                    long id = seqGeneratorService.getNext(SeqGeneratorCode.tran_detail);
                    accountDetail.setId(id);
                    accountDetail.setTranId(transInfo.getId());
                    accountDetail.setIndx(indx);
                    if (CdType.CR.equals(accountDetail.getCdType())) {
                        balance = balance.add(accountDetail.getAmount());
                        accountDetail.setBalance(balance);
                        acctOrigBalance.put(acctId, balance);
                    } else if (CdType.DR.equals(accountDetail.getCdType())) {
                        balance = balance.subtract(accountDetail.getAmount());
                        accountDetail.setBalance(balance);
                        acctOrigBalance.put(acctId, balance);
                    } else {
                        throw new TranException("wrong CdType value: " + accountDetail.getCdType());
                    }
                    accountDetailManager.insert(accountDetail);
                    indx++;
                }
            }

            for (TransInfoVo vo : transInfos) {
                TransInfo transInfo = transInfoManager.get(vo.getId());
                if (null == transInfo) throw new TranException("TransInfo not found. id=" + vo.getId());
                transInfo.setAccountFinish(true);
                transInfo.setTranSuccess(true);
                transInfo.setRespCode(RespCode.SUCCESS.getCode());
                transInfoManager.update(transInfo);
            }
        } catch (DaoException e) {
            throw new TranException(e.getRespCode(), e);
        } catch (Exception e) {
            throw new TranException(RespCode.UNEXPECTED_EXCEPTION, e);
        }
    }

    private boolean checkAccountStatus(TranResponse response, long acctId, BigDecimal amount, UserAccount account) {
        if (null == account) {
            response.setRespCode(RespCode.USER_ACCOUNT_NOT_EXIST);
            response.setDetailMessage("UserAccount not found: id=" + acctId);
            return false;
        }
        if (account.isLocked()) {
            response.setRespCode(RespCode.USER_ACCOUNT_LOCKED);
            response.setDetailMessage("UserAccount locked: id=" + acctId);
            return false;
        }
        if (Global.Utils.isLessThanZero(account.getMoney().add(amount))) {
            response.setRespCode(RespCode.USER_ACCOUNT_LOCKED);
            response.setDetailMessage("UserAccount locked: id=" + acctId);
            return false;
        }
        return true;
    }

    @Override
    public void updateTransInfoRespCode(Long id, RespCode respCode, boolean nullException) {
        TransInfo transInfo = transInfoManager.get(id);
        if (null == transInfo) {
            if (nullException) throw new TranException("TransInfo not found. id=" + id);
            else return;
        }
        transInfo.setRespCode(respCode.getCode());
        transInfoManager.update(transInfo);
    }

}
