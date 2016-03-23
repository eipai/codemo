package com.github.eipai.codemo.benchmark.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.github.eipai.codemo.benchmark.bean.TranRequest;
import com.github.eipai.codemo.benchmark.bean.TranResponse;
import com.github.eipai.codemo.benchmark.bean.TransInfoVo;
import com.github.eipai.codemo.benchmark.utils.Global;

@Service
public class BenchmarkServiceImpl extends AbstractService implements BenchmarkService {

    @Override
    public void test() {
        BigDecimal amount = getRandomTranAmount();
        if (Global.Utils.isLessThanZero(amount)) {
            logger.error("Tran amount is less than zero! do nothing.");
            return;
        }
        int tranSize = getTranSize();
        if (tranSize < 0) {
            logger.error("Tran size is less than zero! do nothing.");
            return;
        }
        List<BigDecimal> amounts = allotAmounts(amount, tranSize);
        long payerId = getRandomPayer();

        TranRequest req = new TranRequest();
        req.setId(new Date().getTime() + "");
        long orderId = Long.parseLong(new SimpleDateFormat("yyMMddHHmmss").format(new Date()));
        orderId = Long.parseLong(orderId + "" + RandomStringUtils.randomNumeric(6));
        List<TransInfoVo> tis = new ArrayList<>();
        TransInfoVo ti = null;
        if (tranSize == 1) {
            ti = new TransInfoVo();
            ti.setOrderId(orderId + "");
            ti.setAmount(amount);
            ti.setPayer(payerId);
            ti.setReceiver(getRandomReceiver());
            tis.add(ti);
        } else {
            StringBuilder ris = new StringBuilder();
            for (int i = 0; i < tranSize; i++) {
                if (i != 0) ris.append("|");
                ris.append(getRandomReceiver() + ":" + amounts.get(i));
            }
            ti = new TransInfoVo();
            ti.setOrderId(orderId + "");
            ti.setAmount(amount);
            ti.setPayer(payerId);
            ti.setReceiversInfo(ris.toString());
            tis.add(ti);
        }

        req.setTransInfos(tis);
        logger.info(">>>>>>>>Tran Test.BEGIN. req=" + req);
        TranResponse resp = tranApiService.tran(req);
        logger.info("<<<<<<<<Tran Test.  END. resp=" + resp);
    }

}
