package com.github.eipai.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class EchoServiceImpl implements EchoService {
    protected final Log logger = LogFactory.getLog(getClass());

    public String echo(String test) {
        logger.info("Hello, " + test);
        return "Hello, " + test;
    }

    public String echo(String test, int times) {
        for (int i = 0; i < times; i++) {
            echo(test);
        }
        return "Hello, " + test;
    }

}
