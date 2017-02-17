package com.maju.common.web.session;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

/**
 * Session供应类
 * @author cl
 *
 */
public interface SessionProvider {
	//向Session里写值
	//name constatns buyer_Session
	//value 用户对象
	public void setAttribute(HttpServletRequest request, String name, Serializable value);
	//获取Session里的值
	public Serializable getAttribute(HttpServletRequest request, String name);
	//退出
	public void logout(HttpServletRequest request);
	//获取SessionId
	public String getSessionId(HttpServletRequest request);
}
