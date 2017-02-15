package cn.maju.goods.admin.book.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.maju.goods.book.domain.Book;
import cn.maju.goods.book.service.BookService;
import cn.maju.goods.category.dao.CategoryDao.Category;
import cn.maju.goods.category.service.CategoryService.CategoryService;
import cn.maju.goods.page.PageBean;

public class AdminBookServlet extends BaseServlet {
	private BookService bookService = new BookService();
	private CategoryService  categoryService = new CategoryService();
	
	/**
	 * 删除图书
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String bid = req.getParameter("bid");
		
		/*
		 * 删除图片
		 */
		Book book = bookService.load(bid);
		String savepath = this.getServletContext().getRealPath("/");//获取真实的路径
		new File(savepath, book.getImage_w()).delete();//删除文件
		new File(savepath, book.getImage_b()).delete();//删除文件
		
		bookService.delete(bid);//删除数据库的记录
		
		req.setAttribute("msg", "删除图书成功！");
		req.setAttribute("code", "success");
		req.setAttribute("backLick", "/admin/AdminBookServlet?method=findAll");
		return "f:/back_page/msg.jsp";
	}
	
	/**
	 * 预修改图书
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String editPre(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取所有一级分类，保存之
		 */
		List<Category> parents = categoryService.findParents();
		req.setAttribute("parents", parents);
		
		/*
		 * 2. 获取bid，得到Book对象，保存之
		 */
		String bid = req.getParameter("bid");
		Book book = bookService.load(bid);
		Category category = categoryService.load(book.getCid());
		if(null != category && null != category.getParent()){
			category.setParent(categoryService.load(category.getParent().getCid()));;
		}
		book.setCategory(category);
		req.setAttribute("book", book);
		
		return "f:/back_page/product/edit.jsp";
	}
	
	/**
	 * 修改图书
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
		Map<String,Object> map = new HashMap<String,Object>();
		for(FileItem fileItem : fileItemList) {
			if(fileItem.isFormField()) {//如果是普通表单字段
				map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
			}
		}
		Book book = CommonUtils.toBean(map, Book.class);//把Map中大部分数据封装到Book对象中
		Category category = CommonUtils.toBean(map, Category.class);//把Map中cid封装到Category中
		book.setCategory(category);
		if(book.getEdition() == null){
			book.setEdition(0);
		}
		if(book.getPageNum() == null){
			book.setPageNum(0);
		}
		if(book.getWordNum() == null){
			book.setWordNum(0);
		}
		if(book.getBooksize() == null){
			book.setBooksize(0);
		}
		bookService.edit(book);
		request.setAttribute("msg", "修改图书成功！");
		request.setAttribute("code", "success");
		request.setAttribute("backLick", "/admin/AdminBookServlet?method=findAll");
		return "f:/back_page/msg.jsp";
	}
	
	/**
	 * 加载图书
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取bid，得到Book对象，保存之
		 */
		String bid = req.getParameter("bid");
		Book book = bookService.load(bid);
		Category category = categoryService.load(book.getCid());
		if(null != category && null != category.getParent()){
			category.setParent(categoryService.load(category.getParent().getCid()));;
		}
		book.setCategory(category);
		req.setAttribute("book", book);
		
		/*
		 * 2. 获取所有一级分类，保存之
		 */
		req.setAttribute("parents", categoryService.findParents());
		/*
		 * 3. 获取当前图书所属的一级分类下所有2级分类
		 */
		/*String pid = book.getCategory().getParent().getCid();
		req.setAttribute("children", categoryService.findChildren(pid));*/
		
		/*
		 * 4. 转发到desc.jsp显示
		 */
		return "f:/back_page/product/desc.jsp";
	}
	
	/**
	 * 添加图书：第一步
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addPre(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取所有一级分类，保存之
		 * 2. 转发到add.jsp，该页面会在下拉列表中显示所有一级分类
		 */
		List<Category> parents = categoryService.findParents();
		req.setAttribute("parents", parents);
		return "f:/back_page/product/add.jsp";
	}
	
	/**
	 * 添加图书:第二步
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
		Map<String,Object> map = new HashMap<String,Object>();
		for(FileItem fileItem : fileItemList) {
			if(fileItem.isFormField()) {//如果是普通表单字段
				map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
			}
		}
		Book book = CommonUtils.toBean(map, Book.class);//把Map中大部分数据封装到Book对象中
		Category category = CommonUtils.toBean(map, Category.class);//把Map中cid封装到Category中
		book.setCategory(category);
		book.setBid(CommonUtils.uuid());
		if(book.getEdition() == null){
			book.setEdition(0);
		}
		if(book.getPageNum() == null){
			book.setPageNum(0);
		}
		if(book.getWordNum() == null){
			book.setWordNum(0);
		}
		if(book.getBooksize() == null){
			book.setBooksize(0);
		}
		BookService bookService = new BookService();
		bookService.add(book);
		request.setAttribute("msg", "添加图书成功！");
		request.setAttribute("code", "success");
		request.setAttribute("backLick", "/admin/AdminBookServlet?method=findAll");
		return "f:/back_page/msg.jsp";
	}
	
	public String ajaxFindChildren(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取pid
		 * 2. 通过pid查询出所有2级分类
		 * 3. 把List<Category>转换成json，输出给客户端
		 */
		String pid = req.getParameter("pid");
		List<Category> children = categoryService.findChildren(pid);
		String json = toJson(children);
		resp.getWriter().print(json);
		return null;
	}
	
	// {"cid":"fdsafdsa", "cname":"fdsafdas"}
	private String toJson(Category category) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"cid\"").append(":").append("\"").append(category.getCid()).append("\"");
		sb.append(",");
		sb.append("\"cname\"").append(":").append("\"").append(category.getCname()).append("\"");
		sb.append("}");
		return sb.toString();
	}
	
	// [{"cid":"fdsafdsa", "cname":"fdsafdas"}, {"cid":"fdsafdsa", "cname":"fdsafdas"}]
	private String toJson(List<Category> categoryList) {
		StringBuilder sb = new StringBuilder("[");
		for(int i = 0; i < categoryList.size(); i++) {
			sb.append(toJson(categoryList.get(i)));
			if(i < categoryList.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * 显示所有分类
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findCategoryAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 通过service得到所有的分类
		 * 2. 保存到request中，转发到left.jsp
		 */
		List<Category> parents = categoryService.findAll();
		req.setAttribute("parents", parents);
		return "f:/adminjsps/admin/book/left.jsp";
	}
	
	/**
	 * 显示多有书：分页显示
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		int pc = getPc(req);
		/*
		 * 2. 得到url：...
		 */
		String url = getUrl(req);
		
		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
		PageBean<Book> pb = bookService.findAll(pc);
		//设置分类
		for(Book book: pb.getDataList()){
			if(book.getCid() != null && !book.getCid().equals("")){
				book.setCategory(categoryService.load(book.getCid()));
			}
		}
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		/**
		 * 加载分类
		 */
		List<Category> parents = categoryService.findAll();
		req.setAttribute("parents", parents);
		return "f:/back_page/product/list.jsp";
	}
	
	
	
	
	
	
	/**
	 * 获取当前页码
	 * @param req
	 * @return
	 */
	private int getPc(HttpServletRequest req) {
		int pc = 1;
		String param = req.getParameter("pc");
		if(param != null && !param.trim().isEmpty()) {
			try {
				pc = Integer.parseInt(param);
			} catch(RuntimeException e) {}
		}
		return pc;
	}
	
	/**
	 * 截取url，页面中的分页导航中需要使用它做为超链接的目标！
	 * @param req
	 * @return
	 */
	/*
	 * http://localhost:8080/goods/BookServlet?methed=findByCategory&cid=xxx&pc=3
	 * /goods/BookServlet + methed=findByCategory&cid=xxx&pc=3
	 */
	private String getUrl(HttpServletRequest request) {
		String url=request.getRequestURI()+"?"+request.getQueryString();
		//看看url里有没有pc参数
		int fromIndex=url.lastIndexOf("&pc=");
		if(fromIndex==-1){
			//没有pc参数，直接返回url
			return url;
		}
		//看看pc参数后面是否还有参数
		int toIndex=url.indexOf("&",fromIndex+1);
		if(toIndex==-1){
			//pc参数后面没有参数
			return url.substring(0,fromIndex);
		}else {
			//pc参数后面还有参数
			return url.substring(0,fromIndex)+url.substring(toIndex);
		}
	}
	
	/**
	 * 按分类查
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCategory(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		int pc = getPc(req);
		/*
		 * 2. 得到url：...
		 */
		String url = getUrl(req);
		/*
		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 */
		String cid = req.getParameter("cid");
		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
		PageBean<Book> pb = bookService.findByCategory(cid, pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/adminjsps/admin/book/list.jsp";
	}
	
	/**
	 * 按作者查
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByAuthor(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		int pc = getPc(req);
		/*
		 * 2. 得到url：...
		 */
		String url = getUrl(req);
		/*
		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 */
		String author = req.getParameter("author");
		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
		PageBean<Book> pb = bookService.findByAuthor(author, pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/adminjsps/admin/book/list.jsp";
	}
	
	/**
	 * 按出版社查询
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByPress(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		int pc = getPc(req);
		/*
		 * 2. 得到url：...
		 */
		String url = getUrl(req);
		/*
		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 */
		String press = req.getParameter("press");
		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
		PageBean<Book> pb = bookService.findByPress(press, pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/adminjsps/admin/book/list.jsp";
	}
	
	/**
	 * 按图名查
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByBname(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		int pc = getPc(req);
		/*
		 * 2. 得到url：...
		 */
		String url = getUrl(req);
		/*
		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 */
		String bname = req.getParameter("bname");
		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
		PageBean<Book> pb = bookService.findByBname(bname, pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		return "f:/adminjsps/admin/book/list.jsp";
	}
	
	/**
	 * 多条件组合查询
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCombination(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		int pc = getPc(req);
		/*
		 * 2. 得到url：...
		 */
		String url = getUrl(req);
		/*
		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 */
		Book criteria = CommonUtils.toBean(req.getParameterMap(), Book.class);
		if(StringUtils.isNotBlank(criteria.getCid())){
			Category category = categoryService.load(criteria.getCid());
			criteria.setCategory(category);
		}
		String pid = req.getParameter("pid");
		if(!StringUtils.isNotBlank(criteria.getCid())&&StringUtils.isNotBlank(pid)){
			List<Category> children = categoryService.findChildren(pid);
			if(null!=children && children.size()>0){
				StringBuilder sb = new StringBuilder();
				for(Category c:children){
					sb.append("'"+c.getCid()+"',");
				}
				criteria.setCid(sb.substring(1, sb.length()-2));
			}
		}
		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
		PageBean<Book> pb = bookService.findByCombination(criteria, pc);
		for(Book book:pb.getDataList()){
			Category category = categoryService.load(book.getCid());
			if(null != category && null != category.getParent()){
				category.setParent(categoryService.load(category.getParent().getCid()));;
			}
			book.setCategory(category);
		}
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
		pb.setUrl(url);
		req.setAttribute("pb", pb);
		req.setAttribute("criteria", criteria);
		req.setAttribute("criteriaPid", pid);
		/**
		 * 加载分类
		 */
		List<Category> parents = categoryService.findAll();
		req.setAttribute("parents", parents);
		return "f:/back_page/product/list.jsp";
	}
	
	public String deletes(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String bids = req.getParameter("bids");
		String[] bidStrings = bids.split(",");
		for(int i=0;i<bidStrings.length;i++){
			/*
			 * 删除图片
			 */
			Book book = bookService.load(bidStrings[i]);
			String savepath = this.getServletContext().getRealPath("/");//获取真实的路径
			new File(savepath, book.getImage_w()).delete();//删除文件
			new File(savepath, book.getImage_b()).delete();//删除文件
			bookService.delete(bidStrings[i]);//删除数据库的记录
		}
		req.setAttribute("msg", "删除图书成功！");
		req.setAttribute("code", "success");
		req.setAttribute("backLick", "/admin/AdminBookServlet?method=findAll");
		return "f:/back_page/msg.jsp";
	}
	
}
