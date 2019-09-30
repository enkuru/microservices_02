package com.enkuru.microservices.netflixzuulapigatewayserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class ZuulLoggingFilter extends ZuulFilter {
    private Logger logger = LoggerFactory.getLogger(ZuulLoggingFilter.class);

    @Override
    public String filterType() {// this method indicates when is the filter should filtering, for example before-pre, after-post, error-error
        return "pre";
    }

    @Override
    public int filterOrder() {// ıf we have multiple filters, with this ordering we can set a priority between them
        return 1;
    }

    @Override
    public boolean shouldFilter() {// this filter should work or not, we set it true as we are wanting it to work for each request
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();

        logger.info("request -> {} request uri -> {}", request, request.getRequestURI());

        return null;
    }
}
