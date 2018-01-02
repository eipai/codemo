package com.github.eipai.shardingjdbc.mybatis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.eipai.shardingjdbc.mybatis.entity.Order;
import com.github.eipai.shardingjdbc.mybatis.entity.OrderItem;
import com.github.eipai.shardingjdbc.mybatis.repository.OrderItemRepository;
import com.github.eipai.shardingjdbc.mybatis.repository.OrderRepository;

@Service
public class DemoService {
    static public Logger logger = LoggerFactory.getLogger(DemoService.class);

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private OrderItemRepository orderItemRepository;

    @Transactional
    public void queryTest() {
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", 100);
        params.put("order_id", 201L);

        logger.info("========1========\r\n"+ orderRepository.query(params));
    }

    public void initTables() {
        orderRepository.createIfNotExistsTable();
        orderItemRepository.createIfNotExistsTable();
    }

    public void dropTables() {
        orderItemRepository.dropTable();
        orderRepository.dropTable();
    }

    public void demo() {
        orderRepository.createIfNotExistsTable();
        orderItemRepository.createIfNotExistsTable();
        orderRepository.truncateTable();
        orderItemRepository.truncateTable();
        List<Long> orderIds = new ArrayList<>(10);
        System.out.println("1.Insert--------------");
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setUserId(51);
            order.setStatus("INSERT_TEST");
            orderRepository.insert(order);
            long orderId = order.getOrderId();
            orderIds.add(orderId);

            OrderItem item = new OrderItem();
            item.setOrderId(orderId);
            item.setUserId(51);
            item.setStatus("INSERT_TEST");
            orderItemRepository.insert(item);
        }
        System.out.println(orderItemRepository.selectAll());
        System.out.println("2.Delete--------------");
        for (Long each : orderIds) {
            orderRepository.delete(each);
            orderItemRepository.delete(each);
        }
        System.out.println(orderItemRepository.selectAll());
        orderItemRepository.dropTable();
        orderRepository.dropTable();
    }
}
