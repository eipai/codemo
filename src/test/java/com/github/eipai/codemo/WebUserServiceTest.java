package com.github.eipai.codemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.eipai.codemo.domain.WebUser;
import com.github.eipai.codemo.service.WebUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class WebUserServiceTest {
    @Autowired
    private WebUserService webUserService;

    public void testGet() {
        WebUser user = webUserService.getById("1001");
        System.out.println("======>:\r\n" + user);
    }

    @Test
    public void testTransaction() {
        webUserService.transDemo("1001");
    }
}
