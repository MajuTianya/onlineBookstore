package com.maju.core.admin.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.maju.common.pagination.PageBean;
import com.maju.common.pagination.PageConstants;
import com.maju.common.web.ConstantsCommon;
import com.maju.common.web.staticpage.StaticPageService;
import com.maju.core.admin.category.bean.Category;
import com.maju.core.admin.category.service.CategoryService;
import com.maju.core.admin.product.bean.Product;
import com.maju.core.admin.product.service.ProductService;

@Controller
@RequestMapping(value = "/admin/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private StaticPageService staticPageService;
	
	/**
	 * 获取所有的商品
	 * @author 	陈丽
	 * @Date 	2016年05月27日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/list.do")
	public String list(Product product, String pid, Integer pc, ModelMap model){
		int pageCode = PageBean.pc(pc);
		int pageSize = PageConstants.ORDER_PAGE_SIZE;
		int startRow = PageBean.startRow(pageCode,pageSize);
		StringBuilder params = new StringBuilder();
		if(null!=product){
			if(StringUtils.isNotBlank(product.getBname())){
				params.append("bname="+product.getBname()+"&");
			}
			if(StringUtils.isNotBlank(product.getAuthor())){
				params.append("author="+product.getAuthor()+"&");
			}
			if(StringUtils.isNotBlank(product.getPress())){
				params.append("press="+product.getPress()+"&");
			}
			if(StringUtils.isNotBlank(product.getCid())){
				params.append("cid="+product.getCid()+"&");
			}
			if(StringUtils.isNotBlank(pid)){
				params.append("pid="+pid+"&");
			}
		}
		
		PageBean<Product> pb = productService.getAllProduct(product,startRow,pageSize);
		for(Product p: pb.getDataList()){
			if(p.getCid() != null && !p.getCid().equals("")){
				p.setCategory(categoryService.getCategoryByCid(p.getCid()));
				p.setImage_b(ConstantsCommon.IMAGE_WEB_URL+p.getImage_b());
				p.setImage_w(ConstantsCommon.IMAGE_WEB_URL+p.getImage_w());
			}
		}
		pb.setPc(pageCode);
		String url =ConstantsCommon.PROJECT_NAME_URL+"/admin/product/list.do";
		pb.setUrl(pb.url(url, params.toString()));
		model.addAttribute("pb", pb);
		
		/**
		 * 加载一级分类
		 */
		List<Category> parents = categoryService.getParents();
		model.addAttribute("parents", parents);
		
		/**
		 * 查询条件回显
		 */
		if(StringUtils.isNotBlank(product.getCid())){
			product.setCategory(categoryService.getCategoryByCid(product.getCid()));
		}
		model.addAttribute("criteria", product);
		model.addAttribute("pid", pid);
		return "product/list";
	}
	
	/**
	 * 商品添加跳转
	 * @author 	陈丽
	 * @Date 	2016年05月27日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/toAdd.do")
	public String toAdd(ModelMap model){
		List<Category> parents = categoryService.getParents();
		model.addAttribute("parents", parents);
		return "product/add";
	}
	
	/**
	 * 商品添加
	 * @author 	陈丽
	 * @Date 	2016年05月27日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/add.do")
	public String add(Product product, ModelMap model){
		if(product !=null
				& StringUtils.isNotBlank(product.getBname())
				& product.getCurrPrice() !=null
				& product.getPrice() !=null
				& product.getDiscount() !=null
				& StringUtils.isNotBlank(product.getAuthor())
				& StringUtils.isNotBlank(product.getPress())
				& StringUtils.isNotBlank(product.getCid())
				& StringUtils.isNotBlank(product.getImage_b())
				& StringUtils.isNotBlank(product.getImage_w())
				){
			product.setBid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
			Integer i = productService.addProduct(product);
			if(i>0){
				model.addAttribute("msg", "添加图书成功！");
				model.addAttribute("code", "success");
			}else{
				model.addAttribute("msg", "添加图书失败！");
				model.addAttribute("code", "errot");
			}
		}else{
			model.addAttribute("msg", "添加图书失败！");
			model.addAttribute("code", "errot");
		}
		model.addAttribute("backLick", "/admin/product/list.do");
		return "msg";
	}
	
	/**
	 * 加载子类
	 * @author 	陈丽
	 * @Date 	2016年05月27日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/ajaxFindChildren.do")
	/*public void ajaxFindChildren(Category category, HttpServletResponse response){
		
		 * 1. 获取pid
		 * 2. 通过pid查询出所有2级分类
		 * 3. 把List<Category>转换成json，输出给客户端
		 
		List<Category> children = categoryService.queryCategoryByCategories(category);
		JSONObject json = new JSONObject();
		try {
			json.put("children",children);
		} catch (JSONException jsone) {
			// TODO Auto-generated catch block
			throw new RuntimeException(jsone);
		}
		ResponseUtils.senderJson(json.toString(), response);
	}*/
	public void ajaxFindChildren(HttpServletRequest req, HttpServletResponse resp){
		/*
		 * 1. 获取pid
		 * 2. 通过pid查询出所有2级分类
		 * 3. 把List<Category>转换成json，输出给客户端
		 */
		String pid = req.getParameter("pid");
		List<Category> children = categoryService.getChildrenByPid(pid);
		String json = toJson(children);
		try {
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/json");
			resp.getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 * 根据ID加载商品
	 * @author 	陈丽
	 * @Date 	2016年05月27日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/getProductById.do")
	public String getProductById(String bid, ModelMap model){
		if(StringUtils.isNotBlank(bid)){
			Product product = productService.getProductById(bid);
			if(null!=product && StringUtils.isNotBlank(product.getCid())){
				Category category = categoryService.getCategoryByCid(product.getCid());
				category.setParent(categoryService.getCategoryByCid(category.getPid()));
				product.setCategory(category);
			}
			model.addAttribute("book", product);
			return "product/desc";
		}
		return null;
	}
	
	/**
	 * 修改商品跳转
	 * @author 	陈丽
	 * @Date 	2016年05月27日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/toEdit.do")
	public String toEdit(String bid, ModelMap model){
		if(StringUtils.isNotBlank(bid)){
			Product product = productService.getProductById(bid);
			if(null!=product && StringUtils.isNotBlank(product.getCid())){
				Category category = categoryService.getCategoryByCid(product.getCid());
				//category.setParent(categoryService.getCategoryByCid(category.getPid()));
				product.setCategory(category);
			}
			model.addAttribute("book", product);
			//下拉框用的
			List<Category> parents = categoryService.getParents();
			model.addAttribute("parents", parents);
			return "product/edit";
		}
		return null;
	}
	
	/**
	 * 修改商品
	 * @author 	陈丽
	 * @Date 	2016年05月27日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/edit.do")
	public String edit(Product product, ModelMap model){
		if(null!=product && StringUtils.isNotBlank(product.getBid())){
			Integer i = productService.updateProduct(product);
			if(i>0){
				model.addAttribute("msg", "修改图书成功！");
				model.addAttribute("code", "success");
			}else{
				model.addAttribute("msg", "修改图书失败！");
				model.addAttribute("code", "errot");
			}
			model.addAttribute("backLick", "/admin/product/list.do");
			return "msg";
		}
		return null;
	}
	
	/**
	 * 删除商品
	 * @author 	陈丽
	 * @Date 	2016年05月27日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(String bid, ModelMap model){
		if(StringUtils.isNotBlank(bid)){
			Integer i = productService.deleteProductByKey(bid);
			if(i>0){
				model.addAttribute("msg", "删除图书成功！");
				model.addAttribute("code", "success");
			}else{
				model.addAttribute("msg", "删除图书失败！");
				model.addAttribute("code", "errot");
			}
			model.addAttribute("backLick", "/admin/product/list.do");
			return "msg";
		}
		return null;
	}
	
	/**
	 * 批量删除商品
	 * @author 	陈丽
	 * @Date 	2016年05月27日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/deletes.do")
	public String deletes(String[] ids ,ModelMap model){
		if(ids != null || ids.length > 0){
			Integer i = productService.deleteProductByKeys(ids);
			if(i>0){
				model.addAttribute("msg", "删除图书成功！");
				model.addAttribute("code", "success");
			}else{
				model.addAttribute("msg", "删除图书失败！");
				model.addAttribute("code", "errot");
			}
			model.addAttribute("backLick", "/admin/product/list.do");
			return "msg";
		}
		return null;
	}
	
	/**
	 * 批量上架商品
	 * @author 	陈丽
	 * @Date 	2016年05月27日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/isShows.do")
	public String isShows(String[] ids, ModelMap model){
		if(ids != null || ids.length > 0){
			for(String id : ids){
				//rootMap
				Map<String,Object> rootMap = new HashMap<String, Object>();
				Product product = productService.getProductById(id);
				//model.addAttribute("product", product);
				rootMap.put("book", product);
				staticPageService.productIndex(rootMap, id);
			}
		}
		model.addAttribute("msg", "图书上架成功！");
		model.addAttribute("code", "success");
		model.addAttribute("backLick", "/admin/product/list.do");
		return "msg";
	}
	
}
