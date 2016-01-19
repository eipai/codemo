package com.github.eipai.codemo.webapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HelloworldServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected final Log logger = LogFactory.getLog(getClass());

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        logger.info("===HelloworldServlet.doPost===");
        try {
            res.setContentType("text/html");
            PrintWriter writer = res.getWriter();
            writer.println("<html>");
            writer.println("<head><title>demo</title></head>");
            writer.println("<body>");
            writer.println("<hl>HelloworldServlet</hl>");
            writer.println("</body></html>");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
