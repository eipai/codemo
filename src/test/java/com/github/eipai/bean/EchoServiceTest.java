package com.github.eipai.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class EchoServiceTest {
    @Autowired
    private EchoService echoService;

    @Test
    public void testEcho() {
        String hello = echoService.echo("Andy");
        System.out.println(hello);
    }
}
