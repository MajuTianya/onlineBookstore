package com.maju.core.admin.product.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.maju.common.web.ConstantsCommon;
import com.maju.common.web.ResponseUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import net.fckeditor.response.UploadResponse;

/**
 * 图片上传控制层
 * @author cl
 *
 */
@Controller
@RequestMapping(value = "/admin/upload")
public class UploadController {
	
	/**
	 * 上传小图
	 * @author 	陈丽
	 * @Date 	2016年05月27日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/uploadPicture.do")
	public void uploadPicture(MultipartFile imageb, HttpServletRequest request, HttpServletResponse response){
		/**
		 * 图片名称处理
		 */
		//获取图片名扩展名
		String ext = FilenameUtils.getExtension(imageb.getOriginalFilename());
		//日期
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String format = df.format(new Date());
		//三位随机数
		Random random = new Random();
		for(int i=0;i<3;i++){
			format+=random.nextInt(10);
		}
		
		//实例化jersey客户端
		Client client = new Client();
		//设置保存数据库的路径path
		String path = "book_img/"+format+"."+ext;
		//设置目标地址
		String url = ConstantsCommon.IMAGE_WEB_URL+path;		
		WebResource webResource = client.resource(url);
		//发送方式 post,get,put
		try {
			webResource.put(String.class, imageb.getBytes());//发送方式，发送内容（二进制数组）
		}catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}//以字符串的方式发送，发送的内容
		//ajax异步返回图片路径，json格式
		JSONObject json = new JSONObject();
		try {
			json.put("url",url);
			json.put("path",path);
		} catch (JSONException jsone) {
			// TODO Auto-generated catch block
			throw new RuntimeException(jsone);
		}
		ResponseUtils.senderJson(json.toString(), response);
	}
	
	/**
	 * 上传大图
	 * @author 	陈丽
	 * @Date 	2016年05月27日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/uploadPictureW.do")
	public void uploadPictureW(MultipartFile imagew, HttpServletRequest request, HttpServletResponse response){
		/**
		 * 图片名称处理
		 */
		//获取图片名扩展名
		String ext = FilenameUtils.getExtension(imagew.getOriginalFilename());
		//日期
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String format = df.format(new Date());
		//三位随机数
		Random random = new Random();
		for(int i=0;i<3;i++){
			format+=random.nextInt(10);
		}
		
		//实例化jersey客户端
		Client client = new Client();
		//设置保存数据库的路径path
		String path = "upload/"+format+"."+ext;
		//设置目标地址
		String url = ConstantsCommon.IMAGE_WEB_URL+path;		
		WebResource webResource = client.resource(url);
		//发送方式 post,get,put
		try {
			webResource.put(String.class, imagew.getBytes());//发送方式，发送内容（二进制数组）
		}catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}//以字符串的方式发送，发送的内容
		//ajax异步返回图片路径，json格式
		JSONObject json = new JSONObject();
		try {
			json.put("url",url);
			json.put("path",path);
		} catch (JSONException jsone) {
			// TODO Auto-generated catch block
			throw new RuntimeException(jsone);
		}
		ResponseUtils.senderJson(json.toString(), response);
	}
	//从FCK上传文件到服务器
	@RequestMapping(value = "/uploadFCK.do")
	public void uploadFCK(HttpServletRequest request, HttpServletResponse response){
		//强转request
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;
		//获取文件Map
		Map<String, MultipartFile> fileMap = mr.getFileMap();
		//获取map的entrySet
		Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
		//遍历长传的文件
		for(Entry<String, MultipartFile> entry : entrySet){
			MultipartFile pic = entry.getValue();
			/**
			 * 图片名称处理
			 */
			//获取图片名扩展名
			String ext = FilenameUtils.getExtension(pic.getOriginalFilename());
			//日期
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String format = df.format(new Date());
			//三位随机数
			Random random = new Random();
			for(int i=0;i<3;i++){
				format+=random.nextInt(10);
			}
			
			//实例化jersey客户端
			Client client = new Client();
			//设置保存数据库的路径path
			String path = "book_img/"+format+"."+ext;
			//设置目标地址
			String url = ConstantsCommon.IMAGE_WEB_URL+path;		
			WebResource webResource = client.resource(url);
			//发送方式 post,get,put
			try {
				webResource.put(String.class, pic.getBytes());//发送方式，发送内容（二进制数组）
			}catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}//以字符串的方式发送，发送的内容
			//返回url给fck java-core.java
			UploadResponse ok = UploadResponse.getOK(url);
			//response 返回对象
			try {
				response.getWriter().print(ok);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
