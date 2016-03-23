package com.github.eipai.codemo.benchmark.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.eipai.codemo.benchmark.bean.TranRequest;
import com.github.eipai.codemo.benchmark.bean.TranResponse;
import com.github.eipai.codemo.benchmark.bean.TransInfoVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/applicationContext.xml" })
public class TranServiceTest {
    @Autowired
    private TranApiService tranApiService;

    @Test
    public void testTrans() {
        TranRequest req = new TranRequest();
        req.setId(new Date().getTime() + "");
        long orderId = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        List<TransInfoVo> tis = new ArrayList<>();
        TransInfoVo ti = new TransInfoVo();
        ti.setOrderId(orderId + "");
        ti.setAmount(new BigDecimal("10"));
        ti.setPayer(1);
        ti.setReceiver(2);
        tis.add(ti);
        ti = new TransInfoVo();
        ti.setOrderId((orderId + 1) + "");
        ti.setAmount(new BigDecimal("10"));
        ti.setPayer(2);
        ti.setReceiversInfo("3:1|4:5|5:4");
        tis.add(ti);
        req.setTransInfos(tis);
        TranResponse resp = tranApiService.tran(req);
        System.out.println("======================>" + resp);
    }
}
