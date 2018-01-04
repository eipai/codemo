package com.github.eipai.shardingjdbc.sales.dao;

import java.util.List;
import java.util.Map;

import com.github.eipai.shardingjdbc.sales.po.PayOrder;

public interface PayOrderDao {

    List<PayOrder> query(Map<String, Object> params);

}
