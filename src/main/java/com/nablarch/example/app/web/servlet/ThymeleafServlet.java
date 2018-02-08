package com.nablarch.example.app.web.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import nablarch.core.repository.SystemRepository;

public class ThymeleafServlet extends HttpServlet {

    private ServletContext servletContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final TemplateEngine templateEngine = SystemRepository.get("templateEngine");
        final String template = req.getRequestURI();
        final WebContext context = new WebContext(req, resp, servletContext);
        resp.setCharacterEncoding("UTF-8");
        final Writer writer = resp.getWriter();
        templateEngine.process(template, context, writer);
        writer.flush();
    }
}
