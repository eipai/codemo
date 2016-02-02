package com.github.eipai.codemo.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping("/test/demo.htm")
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		logger.info("ok");
		if (true) {
			// throw new UnauthenticatedException();
		}
		return null;
	}

}
