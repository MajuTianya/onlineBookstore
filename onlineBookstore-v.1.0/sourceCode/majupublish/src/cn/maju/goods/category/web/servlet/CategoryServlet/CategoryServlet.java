package cn.maju.goods.category.web.servlet.CategoryServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;
import cn.maju.goods.category.dao.CategoryDao.Category;
import cn.maju.goods.category.service.CategoryService.CategoryService;

public class CategoryServlet extends BaseServlet {
	
	private CategoryService categoryService=new CategoryService();

	/**
	 * 查询所有的一级、二级分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1、调用categoryService.findAll()得到parents
		 * 		-->查询所有的parent 封装成List<Category> parents
		 * 		-->循环parents
		 * 			-->通过children.pid=parent.cid，可以得到parent对应的List<Category> children
		 * 		-->通过parents就可以循环遍历出它所有的二级分类，只有二级分类才能触发书籍的显示
		 * 2、将parents保存到request中
		 * 3、转发到left.jsp中
		 */
		List<Category> parents=categoryService.findAll();
		request.setAttribute("parents", parents);
		return "f:/jsps/menu/menu.jsp";
	}

}
