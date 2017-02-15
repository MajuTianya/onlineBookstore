package cn.maju.goods.admin.category.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.maju.goods.book.service.BookService;
import cn.maju.goods.category.dao.CategoryDao.Category;
import cn.maju.goods.category.service.CategoryService.CategoryService;

public class AdminCategoryServlet extends BaseServlet {
	private CategoryService categoryService = new CategoryService();
	private BookService bookService = new BookService(); 
	
	/**
	 * 查询所有分类
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Category> parents = categoryService.findAll();
		req.setAttribute("parentss",parents);
		req.setAttribute("parents",parents);
		return "f:/back_page/category/list.jsp";
	}
	
	/**
	 * 条件查询分类
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findCategoryByCategories(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Category category = CommonUtils.toBean(req.getParameterMap(), Category.class); 
		String pid = req.getParameter("pid");
		String nameString = req.getParameter("nameStr");
		if(!nameString.trim().equals("无")){
			req.setAttribute("nameString", nameString);
		}
		if(pid != null && !pid.equals("")){
			Category parent = new Category();
			parent.setCid(pid);
			category.setParent(parent);
		}
		List<Category> parents =categoryService.findCategoryByCategories(category);
		/*for(Category parent:parents){
			System.out.println(parent.getCid()+"descr:"+parent.getCname()+"descr:"+parent.getParent().getCid()+"descr:"+parent.getDescr());
		}*/
		List<Category> parentss =categoryService.findAll();
		req.setAttribute("parentss", parentss);
		req.setAttribute("parents", parents);
		req.setAttribute("category", category);
		return "f:/back_page/category/list.jsp";
	}
	
	/**
	 * 添加分类
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到Category中
		 * 2. 需要手动的把表单中的pid映射到child对象中
		 * 2. 调用service的add()方法完成添加
		 * 3. 调用findAll()，返回list.jsp显示所有分类
		 */
		Category child = CommonUtils.toBean(req.getParameterMap(), Category.class);
		child.setCid(CommonUtils.uuid());//设置cid
		
		// 手动映射pid 
		String pid = req.getParameter("pid");
		Category parent = new Category();
		parent.setCid(pid);
		child.setParent(parent);
		
		categoryService.add(child);
		req.setAttribute("msg", "添加分类成功！");
		req.setAttribute("code", "success");
		req.setAttribute("backLick", "/admin/AdminCategoryServlet?method=findAll");
		return "f:/back_page/msg.jsp";
	}
	
	/**
	 * 添加分类：第一步
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addPre(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Category> parents = categoryService.findParents();
		req.setAttribute("parents", parents);
		
		return "f:/back_page/category/add.jsp";
	}
	
	/**
	 * 修改分类：第一步
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String editPre(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取链接中的cid
		 * 2. 使用cid加载Category
		 * 3. 保存Category
		 * 4. 转发到edit.jsp页面显示Category
		 */
		String cid = req.getParameter("cid");
		Category child = categoryService.load(cid);
		req.setAttribute("child", child);
		List<Category> parents = categoryService.findParents(); 
		req.setAttribute("parents", parents);
		return "f:/back_page/category/edit.jsp";
	}
	
	/**
	 * 修改分类：第二步
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String edit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到Category中
		 * 2. 调用service方法完成修改
		 * 3. 转发到list.jsp显示所有分类（return findAll();）
		 */
		Category category = CommonUtils.toBean(req.getParameterMap(), Category.class);
		String pid = req.getParameter("pid");
		if(pid != null && !pid.equals("")){
			Category parent = new Category();
			parent.setCid(pid);
			category.setParent(parent);
		}
		categoryService.edit(category);
		req.setAttribute("msg", "修改分类成功！");
		req.setAttribute("code", "success");
		req.setAttribute("backLick", "/admin/AdminCategoryServlet?method=findAll");
		return "f:/back_page/msg.jsp";
	}
	
	/**
	 * 删除一级分类
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteParent(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取链接参数cid，它是一个1级分类的id
		 * 2. 通过cid，查看该父分类下子分类的个数
		 * 3. 如果大于零，说明还有子分类，不能删除。保存错误信息，转发到msg.jsp
		 * 4. 如果等于零，删除之，返回到list.jsp
		 */
		String cid = req.getParameter("cid");
		int cnt = categoryService.findChildrenCountByParent(cid);
		if(cnt > 0) {
			req.setAttribute("msg", "该分类下还有子分类，不能删除！");
			req.setAttribute("code", "error");
			req.setAttribute("backLick", "/admin/AdminCategoryServlet?method=findAll");
			return "f:/back_page/msg.jsp";
		} else {
			categoryService.delete(cid);
			req.setAttribute("msg", "删除分类成功！");
			req.setAttribute("code", "success");
			req.setAttribute("backLick", "/admin/AdminCategoryServlet?method=findAll");
			return "f:/back_page/msg.jsp";
		}
	}
	
	/**
	 * 删除2级分类
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteChild(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取cid，即2级分类id
		 * 2. 获取该分类下的图书个数
		 * 3. 如果大于零，保存错误信息，转发到msg.jsp
		 * 4. 如果等于零，删除之，返回到list.jsp
		 */
		String cid = req.getParameter("cid");
		int cnt = bookService.findBookCountByCategory(cid);
		if(cnt > 0) {
			req.setAttribute("msg", "该分类下还存在图书，不能删除！");
			req.setAttribute("code", "error");
			req.setAttribute("backLick", "/admin/AdminCategoryServlet?method=findAll");
			return "f:/back_page/msg.jsp";
		} else {
			categoryService.delete(cid);
			req.setAttribute("msg", "删除分类成功！");
			req.setAttribute("code", "success");
			req.setAttribute("backLick", "/admin/AdminCategoryServlet?method=findAll");
			return "f:/back_page/msg.jsp";
		}
	}
}
