package com.dataart.booksapp.ui.user.authorization;

import com.dataart.booksapp.ui.user.UserSessionData;

import javax.faces.application.ResourceHandler;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vlobyntsev on 15.06.2016.
 */
public class AuthorizationFilter implements Filter {

    private List<String> unsecuredUrls;

    private String loginUrl;

    @Inject
    private UserSessionData userSessionData;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        unsecuredUrls = Arrays.asList(filterConfig.getInitParameter("unsecuredUrls").split(","));
        loginUrl = filterConfig.getInitParameter("loginUrl");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (isResourceRequest(servletRequest) || isUnsecuredRequest(servletRequest) || userSessionData.isAuthenticated()) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else{
            ((HttpServletResponse)servletResponse).sendRedirect(((HttpServletRequest)servletRequest).getContextPath() + loginUrl);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isUnsecuredRequest(ServletRequest request){
        String targetUrl = ((HttpServletRequest)request).getServletPath();
        return loginUrl.equals(targetUrl) || unsecuredUrls.contains(targetUrl);
    }

    private boolean isResourceRequest(ServletRequest servletRequest){HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
       return httpServletRequest.getRequestURI().startsWith(httpServletRequest.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
    }

}
