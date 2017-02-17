package com.maju.common.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
/**
 * ResponseUtils 公共类封装
 * @author cl
 *
 */
public class ResponseUtils {
	/**
	 * 发送各种格式
	 * @param response
	 * @param contextType
	 */
	public static void sender(String content, HttpServletResponse response, String contentType){
		response.setContentType(contentType);
		try {
			response.getWriter().write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	/**
	 * 发送JSON格式
	 * @param content
	 * @param response
	 */
	public static void senderJson(String content, HttpServletResponse response){
		sender(content, response, "application/json;charset=UTF-8");
	}
	/**
	 * 发送XML格式
	 * @param content
	 * @param response
	 */
	public static void senderXml(String content, HttpServletResponse response){
		sender(content, response, "text/xml;charset=UTF-8");
	}
	/**
	 * 发送文本格式
	 * @param content
	 * @param response
	 */
	public static void senderText(String content, HttpServletResponse response){
		sender(content, response, "text/plain;charset=UTF-8");
	}

}
