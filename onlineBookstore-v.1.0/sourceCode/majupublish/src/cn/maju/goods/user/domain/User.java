package cn.maju.goods.user.domain;

import cn.itcast.commons.CommonUtils;

/**
 * 用户模块实体类
 * @Author Administrator
 * */
/*
 * 属性哪里来
 * 1、t_user表：因为我们需要把t_user表查询出的数据封装到User对象中
 * 2、该模块的所有表单
 * */
public class User {
	//对应数据库表数据
	private String uid;
	private String loginname;//登录名
	private String loginpass;//登录密码
	private String email;//邮箱
	private boolean status;//激活状态
	private String activationCode;//激活码，它是唯一值！
	
	//对应注册表单数据
	private String reloginpass;//确认密码
	private String verifyCode;
	
	//修改密码表单数据
	private String newloginpass;//新密码
	
	
	public String getNewloginpass() {
		return newloginpass;
	}
	public void setNewloginpass(String newloginpass) {
		this.newloginpass = newloginpass;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getReloginpass() {
		return reloginpass;
	}
	public void setReloginpass(String reloginpass) {
		this.reloginpass = reloginpass;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getLoginpass() {
		return loginpass;
	}
	public void setLoginpass(String loginpass) {
		this.loginpass = loginpass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean b) {
		this.status = b;
	}
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", loginname=" + loginname + ", loginpass="
				+ loginpass + ", email=" + email + ", status=" + status
				+ ", activationCode=" + activationCode + ", reloginpass="
				+ reloginpass + ", verifyCode=" + verifyCode
				+ ", newloginpass=" + newloginpass + "]";
	}
	
	
	
}
