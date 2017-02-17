package com.maju.common.web.session;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 本地Session
 * @author cl
 *
 */
public class HttpSessionProvider implements SessionProvider{

	//向Session里写值
	@Override
	public void setAttribute(HttpServletRequest request, String name, Serializable value) {
		HttpSession session = request.getSession();
		session.setAttribute(name, value);
	}

	//获取Session里的值
	@Override
	public Serializable getAttribute(HttpServletRequest request, String name) {
		HttpSession session = request.getSession();
		if(session != null){
			return (Serializable)session.getAttribute(name);
		}else{
			return null;
		}
	}

	//退出
	@Override
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session != null){
			session.invalidate();
		}
		//JSEEIONID
	}

	//获取SessionId
	@Override
	public String getSessionId(HttpServletRequest request) {
		//request.getRequestedSessionId();  //Http://localhost:8080/html/sfsf.shtml?JESSIONID=ewrqwrq234123412
		return request.getSession().getId();
	}

}
