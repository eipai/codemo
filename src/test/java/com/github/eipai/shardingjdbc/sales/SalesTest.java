package com.github.eipai.shardingjdbc.sales;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.eipai.shardingjdbc.mybatis.service.DemoService;
import com.github.eipai.shardingjdbc.sales.service.SalesService;

public class SalesTest {
    static public Logger logger = LoggerFactory.getLogger(SalesTest.class);
    private ApplicationContext context;
    SqlSessionFactory sessionFactory;

    @Before
    public void setup() {
        //context = new ClassPathXmlApplicationContext("mybatis_test.xml");
        context = new ClassPathXmlApplicationContext("sales_test.xml");
        sessionFactory = context.getBean(SqlSessionFactory.class);
    }

    @Test
    public void testSale() {
        SalesService salesService = context.getBean(SalesService.class);
        salesService.queryTest();
    }

    //@Test
    public void testDemo() {
        logger.info("===============================================");
        DemoService demoService = context.getBean(DemoService.class);
        // demoService.initTables();
        // demoService.dropTables();

        demoService.queryTest();

    }

}
