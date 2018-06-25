package com.maju.core.admin.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.maju.common.web.ConstantsCommon;
import com.maju.common.web.session.SessionProvider;
import com.maju.core.admin.admin.bean.Admin;
import com.maju.core.web.Constants;
/**
 * Springmvc映射器的拦截器：后台
 * 处理上下文
 * 处理Session
 * 全局
 * @author cl
 *
 */
public class SpringmvcInterceptor implements HandlerInterceptor{
	@Autowired
	private SessionProvider sessionProvider;
	//增加一个管理员账号已便开发方便,上线封口
	private String adminId;
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	//拦截路径
	public static final String INTERCEPTOR_URL = ConstantsCommon.PROJECT_NAME_URL + "/admin/";

	//方法前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(StringUtils.isNotBlank(adminId)){
			Admin adminUserAdmin = new Admin();
			adminUserAdmin.setAdminname("chenli");
			sessionProvider.setAttribute(request, Constants.ADMIN_SESSION, adminUserAdmin);
			request.setAttribute("isLogin", true);
		}else{
		//获取session里的adminUser
		Admin adminUser = (Admin) sessionProvider.getAttribute(request, Constants.ADMIN_SESSION);
		boolean isLogin = false;
		if(null != adminUser){
			isLogin = true;
		}
		/**
		 * 是否拦截
		 * request.getRequestURI()和request.getRequestURL()的区别
		 * 路径：http://localhost:8080/product/product.shtml
		 * request.getRequestURI():/product/product.shtml
		 * request.getRequestURL():http://localhost:8080/product/product.shtml
		 */
		String requestURI = request.getRequestURI();
		if(requestURI.startsWith(INTERCEPTOR_URL)){
			//必须登陆
			if(null == adminUser){
				if(request.getParameter("returnUrl") != null){
					response.sendRedirect(ConstantsCommon.PORT_NAME_URL+"/toLogin.do?returnUrl="+request.getParameter("returnUrl"));
				}else{
					response.sendRedirect(ConstantsCommon.PORT_NAME_URL+"/toLogin.do");
				}
				return false;
			}
		}
		request.setAttribute("isLogin", isLogin);
		}
		return true;
	}

	//方法后
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	//页面渲染后
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
