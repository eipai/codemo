package com.github.eipai.shardingjdbc.mybatis;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.eipai.shardingjdbc.mybatis.service.DemoService;

public class MybatisTest {
    static public Logger logger = LoggerFactory.getLogger(MybatisTest.class);
    private ApplicationContext context;

    @Before
    public void setup() {
        context = new ClassPathXmlApplicationContext("mybatis_test.xml");
    }

    @Test
    public void test() {
        logger.info("===============================================");
        DemoService demoService = context.getBean(DemoService.class);
        // demoService.initTables();
        // demoService.dropTables();
        demoService.queryTest();
    }

}
