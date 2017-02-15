package cn.maju.goods.admin.book.web.servlet;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONException;
import org.json.JSONObject;

import cn.itcast.servlet.BaseServlet;
import cn.maju.goods.web.Constants;
import cn.maju.goods.web.ResponseUtils;

public class UploadServlet extends BaseServlet {
	
	public void uploadPicture(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		List<FileItem> fileItemList = null;
		try {
			fileItemList = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String tuIndex = request.getParameter("tuIndex");
		/**
		 * 图片名称处理
		 */
		//FileItem pic = fileItemList.get(Integer.parseInt(tuIndex));
		FileItem fileItem = fileItemList.get(Integer.parseInt(tuIndex));
		//获取图片名扩展名
		String ext = FilenameUtils.getExtension(fileItem.getName());
		//日期
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String format = df.format(new Date());
		//三位随机数
		Random random = new Random();
		for(int i=0;i<3;i++){
			format+=random.nextInt(10);
		}
		String savepath = this.getServletContext().getRealPath("/book_img");
		String filename = format+"."+ext;
		//设置保存数据库的路径path
		String path = "book_img/"+filename;
		//设置目标地址
		String url = Constants.IMAGE_WEB_URL+path;		
		File destFile = new File(savepath, filename);
		try {
			fileItem.write(destFile);//它会把临时文件重定向到指定的路径，再删除临时文件
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
	

}
