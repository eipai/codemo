package com.github.eipai.codemo.webapp.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.github.eipai.codemo.benchmark.service.BenchmarkService;

@Controller
public abstract class BaseController {
    private static final Log logger = LogFactory.getLog(BaseController.class);
    @Autowired
    protected BenchmarkService benchmarkService;

    static void nocache(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
    }

    static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> data = new HashMap<String, String>();
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String key = (String) names.nextElement();
            Enumeration<String> values = request.getHeaders(key);
            if (values != null) {
                while (values.hasMoreElements()) {
                    String value = (String) values.nextElement();
                    if (data.containsKey(key)) {
                        logger.warn("drop duplicated key-value[" + key + ": " + value + "] in HttpServletRequest Header!");
                    } else {
                        data.put(key, value);
                    }
                }
            }
        }
        return data;
    }

    static Map<String, String> getParameters(HttpServletRequest request) {
        Map<String, String> data = new HashMap<String, String>();
        Map<String, String[]> map = request.getParameterMap();
        for (Iterator<?> iter = map.entrySet().iterator(); iter.hasNext();) {
            @SuppressWarnings("unchecked")
            Map.Entry<String, String[]> element = (Map.Entry<String, String[]>) iter.next();
            String key = element.getKey();
            String[] values = element.getValue();
            if (null != values) {
                for (int i = 0; i < values.length; i++) {
                    if (data.containsKey(key)) {
                        logger.warn("drop duplicated key-value[" + key + ": " + values[i] + "] in HttpServletRequest Parameter!");
                    } else {
                        data.put(key, values[i]);
                    }
                }
            }
        }
        return data;
    }

    protected static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
