package com.github.eipai.shardingjdbc.sales.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.eipai.shardingjdbc.sales.dao.PayOrderDao;

@Service
public class SalesService {

    @Autowired
    private PayOrderDao payOrderDao;

    public void queryTest() {
        Map<String, Object> params = new HashMap<>();
        params.put("request_no", "R001");
        params.put("bank_seq", "B001");

        System.out.println("-------------");
        System.out.println(payOrderDao.query(params));
    }

}
