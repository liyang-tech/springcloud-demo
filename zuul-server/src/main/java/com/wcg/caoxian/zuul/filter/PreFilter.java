package com.wcg.caoxian.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreFilter extends ZuulFilter{
	
	private static final Logger logger = LoggerFactory.getLogger(PreFilter.class);
	
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext currentContext = RequestContext.getCurrentContext();
		HttpServletRequest request = currentContext.getRequest();
		currentContext.addZuulRequestHeader("Authorization", request.getHeader("Authorization"));
		//String address=currentContext.getRequest().getRemoteAddr();
		logger.info("%s request to %s", request.getMethod(), request.getRequestURI().toString());
		return null;
	}

}
