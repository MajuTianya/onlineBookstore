package cn.maju.goods.user.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.management.loading.PrivateClassLoader;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.maju.goods.user.domain.User;
import cn.maju.goods.user.service.UserService;
import cn.maju.goods.user.service.exception.UserException;

public class UserServlet extends BaseServlet {
	private UserService userService=new UserService();
	public String quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, MessagingException {
		request.getSession().removeAttribute("userSession");
		return "r:/jsps/user/login.jsp";
		}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @Author tsxyChenLi
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws MessagingException
	 */
	public String updateLoginpass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, MessagingException {
		/*
		 * 1、将表单数据封装到user对象中;获取session里的用户，判断其是否登录
		 * 2、服务端校验表单数据，并将错误信息封装到map error
		 * 3、判断error是否为空，否-->将error和formUser保存到request中，并转发回原网页
		 * 4、获取session中的user，看其是否为空,是-->msg“您好没有登录”和fromUser保存到request中，并转发回原网页
		 * 5、通过user.getUid()、FormUser.getLoginpass()、formUser.getNewLoginpass()，调用service.update(,,).
		 * 		通过uid、loginpass 调用UserDAO.findByUidAndLoginpass(),为假，抛出UserException
		 * 		判断loginpass和newloginpass是否相同，是，保存msg和User到request中，并转发回原网页。否，调用UserDAO.updatePass(user.getUid,user.getNewLoginpass)
		 * 6、捕获UserException异常，无异常，保存成功信息到request中，并转发到msg.jsp；有异常，保存异常信息到request中，并转发回原网页
		 */
		User userForm=CommonUtils.toBean(request.getParameterMap(),User.class);
		User user=(User)request.getSession().getAttribute("userSession");
		if(user==null){
			request.setAttribute("msg","您还没有登录！");//它转发到哪里mag就会显示在哪里
			request.setAttribute("code", "error");
			return "f:/jsps/msg.jsp";
		}
		Map<String,String> error=validateUpdateLoginpass(userForm,request);
		if(error.size()>0){
			request.setAttribute("error",error);
			request.setAttribute("user", userForm);
			return "f:/jsps/user/pwd.jsp";
		}
		try {
			userService.updateLoginpass(user.getUid(),userForm.getLoginpass(),userForm.getNewloginpass());
			request.setAttribute("msg", "修改密码成功！");
			request.setAttribute("code", "success");
			return "f:/jsps/msg.jsp";
		} catch (UserException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msgError", "原密码错误！");
			request.setAttribute("user", userForm);
			return "f:/jsps/user/pwd.jsp";
		}
	}
	/*
	 * 服务器端校验表单数据，并将错误信息保存到 map error 中
	 */
	private Map<String, String> validateUpdateLoginpass(User userForm,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String,String> error=new HashMap<String,String>();
		
		//校验原密码
		String loginpass=userForm.getLoginpass();
		if(loginpass==null||loginpass.trim().isEmpty()){
			error.put("loginpass", "原密码不能为空！");
		}else if(loginpass.length()<3||loginpass.length()>20){
			error.put("loginpass", "原密码长度必须在3~20之间！");
		}
		
		//校验新密码
		String newloginpass=userForm.getNewloginpass();
		if(newloginpass==null||newloginpass.trim().isEmpty()){
			error.put("newloginpass", "新密码不能为空！");
		}else if(newloginpass.length()<3||newloginpass.length()>20){
			error.put("newloginpass", "新密码长度必须在3~20之间！");
		}
		
		//校验确认密码
		String reloginpass=userForm.getReloginpass();
		if(reloginpass==null||reloginpass.trim().isEmpty()){
			error.put("reloginpass", "确认密码不能为空！");
		}else if(reloginpass.length()<3||reloginpass.length()>20){
			error.put("reloginpass", "确认密码长度必须在3~20之间！");
		}else if(!reloginpass.equals(newloginpass)){
			error.put("reloginpass", "两次密码不一致！");
		}
		
		//校验验证码
		String verifyCode=userForm.getVerifyCode();
		String vCode=(String) request.getSession().getAttribute("vCode");
		if(verifyCode==null||verifyCode.trim().isEmpty()){
			error.put("verifyCode", "验证码不能为空！");
		}else if(verifyCode.length()!=4){
			error.put("verifyCode", "验证码长度必须为4！");
		}else if(!verifyCode.equalsIgnoreCase(vCode)){
			error.put("verifyCode", "错误的饿验证码！");
		}
		
		return error;
	}

	/**
	 * 登录功能
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, MessagingException {
		/*
		 * 1、将表单数据封装到user对象
		 * 2、对表单进行服务器端校验，并将错误信息封装到map中
		 * 3、判断error是否为空，否-->保存error和user信息到session，用于回显,转发为原地址
		 * 4、调用UserService.login(){利用user的用户名和user的密码查询是否存在该用户}得到user对象
		 * 5、判断user是否为空
		 * 		是-->保存错误信息msg"用户名或密码不正确"保存user到session中，用于回显，转发为原地址
		 *		否-->判断user.getStatus()是否为假
		 *				是-->保存错误信息msg“您还没激活”
		 *				否-->1、保存用户到session 2、保存用户名到cookie 3、登陆成功重定向到主页
		 *6、去login.jsp进行页面处理
		 */
		User userForm=CommonUtils.toBean(request.getParameterMap(), User.class);
		Map<String,String> error=validateLogin(userForm,request);
		if(error.size()>0){
			request.setAttribute("error",error);
			request.setAttribute("user", userForm);
			return "f:/jsps/user/login.jsp";
		}
		User user=userService.login(userForm);
		if(user==null){
			request.setAttribute("msgError", "用户名或密码错误！");
			request.setAttribute("user", userForm);
			return "f:/jsps/user/login.jsp";
		}else if(!user.getStatus()){
			request.setAttribute("msgError", "您还没有激活！");
			request.setAttribute("user", userForm);
			return "f:/jsps/user/login.jsp";
		}else{
			//保存用户到Session
			request.getSession().setAttribute("userSession", user);
			
			//用户名到Cookie
			String loginname=user.getLoginname();
			loginname=URLEncoder.encode(loginname,"utf-8");//保存中文
			Cookie cookie=new Cookie("loginname",loginname);
			cookie.setMaxAge(60*60*24*10);//保存十天
			response.addCookie(cookie);//只有从表单获取数据时用request
			//重定向到主页
			return "f:/index.jsp";
		}
	}
	
	/*
	 * 对表单信息进行服务器端校验的具体实现
	 */
	private Map<String, String> validateLogin(User userForm,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String,String> error=new HashMap<String,String>();
		
		//对登录名进行校验
		String loginname=userForm.getLoginname();
		if(loginname==null||loginname.trim().isEmpty()){//!loginname与loginname==null是不一样的
			error.put("loginnameError","用户名不能为空！");
		}else if(loginname.length()<3||loginname.length()>20){
			error.put("loginnameError", "用户名长度必须在3~20之间！");
		}
		
		//对密码进行校验
		String loginpass=userForm.getLoginpass();
		if(loginpass==null||loginpass.trim().isEmpty()){
			error.put("loginpassError", "密码不能为空！");
		}else if(loginpass.length()<3||loginpass.length()>20){
			error.put("loginpasError", "密码长度必须在3~20之间！");
		}
		
		//对验证码进行校验
		String verifyCode=userForm.getVerifyCode();
		String vCode=(String)request.getSession().getAttribute("vCode");
		if(verifyCode==null||verifyCode.trim().isEmpty()){
			error.put("verifyCodeError", "验证码不能为空！");
		}else if(!verifyCode.equalsIgnoreCase(vCode)){
			error.put("verifyCodeError", "错误的验证码！");
		}
		
		return error;
	}

	/**
	 * 激活功能
	 */
	public String activation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, MessagingException {
		/*
		 * 1、获取验证码
		 * 2、把激活码交给service的activation(String)来完成激活！
		 * >service方法有可能抛出异常，把信息拿来，保存到request中，转发到msg.jsp显示
		 * 3、if(user==null) new UserException("无效的激活码！");
		 */
		String code=request.getParameter("activationCode");
		try {
			userService.activation(code);
			request.setAttribute("code", "success");//通知msg.jsp显示对
			request.setAttribute("msg","恭喜，激活成功，请马上登陆！");
		} catch (UserException e) {
			// TODO Auto-generated catch block
			//说明抛出异常
			request.setAttribute("code", "error");//通知msg.jsp显示X
			request.setAttribute("msg", e.getMessage());
		}
		
		return "f:/jsps/msg.jsp";
	}
	
	/**
	 * 表单后台校验:注册功能
	 * @throws MessagingException 
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, MessagingException {
		
		/*
		 * 1、将表单数据封装到User对象中
		 */
		User user=CommonUtils.toBean(request.getParameterMap(), User.class);
		 
		/*
		 * 2、对表单数据进行服务端校验
		 */
		//把错误信息封装到map error中
		Map<String, String> errors=validateRegist(user,request);
		//是否存在校验的错误信息
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			request.setAttribute("user", user);
			return "f:/jsps/user/regist.jsp";
		}
		
		/*
		 * 调用userService完成注册
		 */
		userService.regist(user);
		
		/*
		 * 4、保存注册成功信息，转发到msg.jsp显示
		 */
		request.setAttribute("code", "success");
		request.setAttribute("msg", "恭喜，注册成功！请马上完成激活！"); 
		return "f:/jsps/msg.jsp";
	}
	/*
	 * 注册校验
	 * 对表单字段进行逐个校验，如果有错误，使用当前字段名称为key，错误信息为value，保存到map中.
	 */
	private Map<String, String> validateRegist(User user,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, String> errors=new HashMap<String,String>(); 
		//对loginname进行校检
		String loginname =user.getLoginname();
		if(loginname==null||loginname.trim().isEmpty()){
			errors.put("loginname", "用户名不能为空！");
		}else if(loginname.length()<3||loginname.length()>20){
			errors.put("loginname", "用户名长度必须在3~20之间！");
		}else if(userService.validateLoginname(loginname)){
			errors.put("loginname", "用户名已被注册！");
		}
		
		//对loginpass进行校验
		String loginpass=user.getLoginpass();
		if(loginpass==null||loginpass.trim().isEmpty()){
			errors.put("loginpass","密码不能为空！");
		}else if(loginpass.length()<3||loginpass.length()>20){
			errors.put("loginpass","密码的长度必须在3~20之间！");
		}
		
		//对确认密码进行校验
		String reloginpass=user.getReloginpass();
		if(reloginpass==null||reloginpass.trim().isEmpty()){
			errors.put("reloginpass","确认密码不能为空！");
		}else if(!reloginpass.equals(loginpass)){
			errors.put("reloginpass", "两次输入密码不一致！");
		}
		
		//对Email进行校验
		String email=user.getEmail();
		if(email==null||email.trim().isEmpty()){
			errors.put("email", "邮件不能为空！");
		}else if(!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")){
			errors.put("email", "错误的Email格式！");
		}else if(userService.validateEmail(email)){
			errors.put("email", "该邮件已被注册！");
		}
		
		//对验证码进行校验
		String verifyCode=user.getVerifyCode();
		String vCode=(String)request.getSession().getAttribute("vCode");
		if(verifyCode==null||verifyCode.trim().isEmpty()){
			errors.put("verifyCode", "验证码不能为空！");
		}else if(!verifyCode.equalsIgnoreCase(vCode)){
			errors.put("verifyCode","错误的验证码！");
		}
		
		return errors;
		
	}

	/**
	 * 异步校验登录名
	 */
	public String validateLoginname(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginname=request.getParameter("loginname");
		boolean flag=userService.validateLoginname(loginname);
		response.getWriter().print(flag+"");
		return null;
	}
	
	/**
	 * 异步校验Email
	 */
	public String validateEmail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email=request.getParameter("email");
		boolean flag=userService.validateEmail(email);
		response.getWriter().print(flag+"");
		return null;
	}
	
	/**
	 * 异步校验验证码
	 */
	public String validateVerifyCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String verifyCode=request.getParameter("verifyCode");
		String vCode=(String)request.getSession().getAttribute("vCode");
		boolean flag=vCode.equalsIgnoreCase(verifyCode);
		response.getWriter().print(flag+"");
		return null;
	}

}
