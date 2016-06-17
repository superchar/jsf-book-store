package com.dataart.booksapp.authorization;

import com.dataart.booksapp.presenters.book.BookData;
import com.dataart.booksapp.presenters.user.UserData;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vlobyntsev on 15.06.2016.
 */
public class AuthorizationFilter implements Filter {

    private String loginUrl;


    @Inject
    private UserData userData;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        loginUrl = filterConfig.getInitParameter("loginUrl");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (isLoginRequest(servletRequest) || userData.isAuthenticated()) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else{
            ((HttpServletResponse)servletResponse).sendRedirect(((HttpServletRequest)servletRequest).getContextPath() + loginUrl);
        }
    }

    private boolean isLoginRequest(ServletRequest request){
        return ((HttpServletRequest)request).getServletPath().equals(loginUrl);
    }

    @Override
    public void destroy() {

    }
}
