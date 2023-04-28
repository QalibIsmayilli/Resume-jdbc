package com.company.resume.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName ="SecurityFilter", urlPatterns = "*")
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response
                         ,FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        if(!req.getRequestURI().contains("login")
                && !req.getRequestURI().contains("error")
                && req.getSession().getAttribute("loggedInUser")==null ){
            res.sendRedirect("login");
        }else{
            filterChain.doFilter(req,res);
        }
    }
}
