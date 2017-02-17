package com.maju.common.web.staticpage.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.maju.common.web.ConstantsCommon;
import com.maju.common.web.staticpage.StaticPageService;

import freemarker.template.Configuration;
import freemarker.template.Template;
/**
 * 静态实现类
 * @author cl
 *
 */
public class StaticPageServiceImpl implements StaticPageService,ServletContextAware{
	//配置对象
	private Configuration cfg;
	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer){
		 this.cfg = freeMarkerConfigurer.getConfiguration();
	}
	//根据product的数据和id 生成静态页
	public void productIndex(Map<String, Object> rootMap, String id){
		//输出 从内存到磁盘
		Writer out = null;
		try {
			//--获取模板
			Template template = cfg.getTemplate("productDetail.html");
			//获取输出路径
			String path = getPath("/html/product/"+id+".html");
			//String path = ConstantsCommon.PROJECT_NAME_URL_BUYER+"/html/product/"+id+".html";
			File file = new File(path);
			File parentFile = file.getParentFile();
			if(!parentFile.exists()){
				parentFile.mkdirs();
			}
			out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			//生成开始
			template.process(rootMap, out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭流
			if(out != null){
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//ServletContext
	private ServletContext servletContext;
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	//获取html输出路径
	private String getPath(String path) {
		return servletContext.getRealPath(path);
	}
}
