package com.github.eipai.codemo.benchmark.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.eipai.codemo.webapp.controller.BaseController;

@Controller
public class BenchmarkController extends BaseController {
    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping("/benchmark/tran/default.htm")
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) Integer cnt) {
        logger.info("Recieve benchmark request. cnt=" + cnt);
        if (cnt == null) {
            benchmarkService.test();
        } else {
            for (int i = 0; i < cnt; i++) {
                benchmarkService.test();
            }
        }
        return new ModelAndView("message", "message", "OK");
    }

}
