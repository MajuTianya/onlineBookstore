package cn.maju.goods.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * 1、将request转化为httpServletRequest
		 * 2、获取userSession
		 * 3、判断userSession是否为空:是，拦截，保存未登录信息，跳转到msg.jsp;否，放行。
		 */
		HttpServletRequest req=(HttpServletRequest)request;
		Object userSession=req.getSession().getAttribute("userSession");
		if(userSession==null){
			req.setAttribute("code", "error");
			req.setAttribute("msg","您还没有登录！");
			req.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
		}else{
			chain.doFilter(request, response);
		}
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
