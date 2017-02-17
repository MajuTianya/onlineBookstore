package com.maju.core.admin.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maju.common.encode.MD5Pwd;
import com.maju.common.web.session.SessionProvider;
import com.maju.core.admin.admin.bean.Admin;
import com.maju.core.admin.admin.service.AdminService;
import com.maju.core.web.Constants;

/**
 * 后台管理
 * @author 陈丽
 *
 */
@Controller
public class AdminController {
	
	@Autowired
	AdminService adminService;
	@Autowired
	private SessionProvider sessionProvider;
	@Autowired
	private MD5Pwd md5Pwd;
	
	//每一个Springmvc
	@RequestMapping(value = "/admin/test/springmvc.do")
	public String test(String name,Date birthday){
		System.out.println();
		return "";
	}

/*	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		//转换日期格式
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		
	}
	*/
	
	//跳转登录页面
	@RequestMapping(value = "/toLogin.do")
	public String toLogin(){
		return "login";
	}
	
	//跳转入口页面
	@RequestMapping(value = "/admin/index.do")
	public String index(){
		return "index";
	}
	//跳转头页面
	@RequestMapping(value = "/admin/top.do")
	public String top(){
		return "top";
	}
	//跳转身体页面
	@RequestMapping(value = "/admin/main.do")
	public String main(){
		return "main";
	}
	//跳转左页面
	@RequestMapping(value = "/admin/left.do")
	public String left(){
		return "left";
	}
	//跳转右页面
	@RequestMapping(value = "/admin/right.do")
	public String right(){
		return "right";
	}
	
	//跳转商品主
	@RequestMapping(value = "/admin/frame/product_main.do")
	public String productMain(){
		return "frame/product_main";
	}
	
	//跳转商品左
	@RequestMapping(value = "/admin/frame/product_left.do")
	public String productLeft(){
		return "frame/product_left";
	}
	
	//跳转订单主
	@RequestMapping(value = "/admin/frame/order_main.do")
	public String orderMain(){
		return "frame/order_main";
	}
	
	//跳转订单左
	@RequestMapping(value = "/admin/frame/order_left.do")
	public String orderLeft(){
		return "frame/order_left";
	}
	
	/**
	 * 登录
	 * @author 	陈丽
	 * @Date 	2016年05月25日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/login.do")
	public String login(Admin adminUser,String returnUrl,ModelMap model,HttpServletRequest request){
		if(null != adminUser && StringUtils.isNotBlank(adminUser.getAdminname())){
			if(StringUtils.isNotBlank(adminUser.getAdminpwd())){
				Admin a = adminService.getAdminByName(adminUser.getAdminname());
				if(null != a){
					//md5Pwd.encode(adminUser.getAdminpwd())TODO
					if(a.getAdminpwd().equals(adminUser.getAdminpwd())){
						//把用户对象放在Session
						sessionProvider.setAttribute(request, Constants.ADMIN_SESSION, a);
						if(StringUtils.isNotBlank(returnUrl)){
							return "redirect:" + returnUrl;
						}else{
							//后台首页
							return "redirect:/admin/index.do" ;
						}
					}else{
						model.addAttribute("msg", "登录密码错误");
					}
				}else{
					model.addAttribute("msg", "登录名称输入错误");
				}
			}else{
				model.addAttribute("msg", "请输入登录密码");
			}
		}else{
			model.addAttribute("msg", "请输入登录名称");
		}
		return "login";
	}
	
	/**
	 * 退出
	 * @author 	陈丽
	 * @Date 	2016年05月25日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/admin/logout.do")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute(Constants.ADMIN_SESSION);
		return "login";
	}

}
