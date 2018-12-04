package com.github.eipai.mybatis.simple;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

    private ApplicationContext context;
    SqlSessionFactory sessionFactory;

    @Before
    public void setup() {
        context = new ClassPathXmlApplicationContext("com/github/eipai/mybatis/simple/mybatis.xml");
        sessionFactory = context.getBean(SqlSessionFactory.class);
    }

    @Test
    public void test() throws InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdfMs = new SimpleDateFormat("SSS");
        OrderDao orderDao = context.getBean(OrderDao.class);
        for (int i = 0; i < 100; i++) {
            Date date = new Date();
            int ms = Integer.parseInt(sdfMs.format(date));
            if (ms > 10 && ms < 990) {
                Thread.sleep(990 - ms);
                continue;
            }
            System.out.println(i + " - " + ms);
            OrderEntity entity = new OrderEntity();
            entity.setTimeDate(date);
            entity.setTimeLong(Long.parseLong(sdf.format(date)));
            orderDao.insert(entity);
        }
    }

}
