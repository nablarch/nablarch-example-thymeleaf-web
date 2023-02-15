package com.nablarch.example.app.web.servlet;

import java.io.IOException;
import java.io.Writer;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import nablarch.core.ThreadContext;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import nablarch.core.repository.SystemRepository;
import org.thymeleaf.web.servlet.IServletWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

/**
 * エラーページへ遷移するためのサーブレット。
 * @author Taichi Uragami
 *
 */
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

        JakartaServletWebApplication application
                = JakartaServletWebApplication.buildApplication(servletContext);
        IServletWebExchange exchange = application.buildExchange(req, resp);
        final WebContext context = new WebContext(exchange, ThreadContext.getLanguage());
        
        resp.setCharacterEncoding("UTF-8");
        final Writer writer = resp.getWriter();
        templateEngine.process(template, context, writer);
        writer.flush();
    }
}
