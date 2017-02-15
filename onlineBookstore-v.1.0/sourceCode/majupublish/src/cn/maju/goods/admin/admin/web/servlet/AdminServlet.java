package cn.maju.goods.admin.admin.web.servlet;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.maju.goods.admin.admin.domain.Admin;
import cn.maju.goods.admin.admin.service.AdminService;

public class AdminServlet extends BaseServlet {
	private AdminService adminService = new AdminService();
	
	/**
	 * 登录功能
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到Admin
		 */
		Admin form = CommonUtils.toBean(req.getParameterMap(), Admin.class);
		Admin admin = adminService.login(form);
		if(admin == null) {
			req.setAttribute("msg", "用户名或密码错误！");
			return "f:/back_page/login.jsp";
		}
		req.getSession().setAttribute("admin", admin);
		return "r:/back_page/index.jsp";
	}
	
	public String logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, MessagingException {
		request.getSession().removeAttribute("admin");
		return "r:/back_page/login.jsp";
		}
}
