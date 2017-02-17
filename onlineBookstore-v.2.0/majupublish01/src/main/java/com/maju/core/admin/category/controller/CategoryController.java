package com.maju.core.admin.category.controller;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maju.core.admin.category.bean.Category;
import com.maju.core.admin.category.service.CategoryService;

@Controller
@RequestMapping(value = "/admin/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 查询所有分类
	 * @author 	陈丽
	 * @Date 	2016年05月26日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/getAllCategory.do")
	public String getAllCategory(ModelMap model){
		List<Category> categorys = categoryService.getAllCategory();
		model.addAttribute("parentss",categorys);
		model.addAttribute("parents",categorys);
		return "category/list";
	}
	
	//跳转增加
	@RequestMapping(value = "/toAdd.do")
	public String toAdd(ModelMap model){
		List<Category> parents = categoryService.getParents();
		model.addAttribute("parents", parents);
		return "category/add";
	}
	
	/**
	 * 添加分类
	 * @author 	陈丽
	 * @Date 	2016年05月26日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/add.do")
	public String add(Category category, ModelMap model){
		category.setCid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		Integer i = categoryService.add(category);
		if(i>0){
			model.addAttribute("msg", "添加分类成功！");
			model.addAttribute("code", "success");
		}else{
			model.addAttribute("msg", "添加分类失败！");
			model.addAttribute("code", "error");
		}
		model.addAttribute("backLick", "/admin/category/getAllCategory.do");
		return "msg";
	}
	
	//跳转修改
	@RequestMapping(value = "/toEdit.do")
	public String toEdit(String cid, ModelMap model){
		if(StringUtils.isNotBlank(cid)){
			Category category = categoryService.getCategoryByCid(cid);
			model.addAttribute("child", category);
			//下拉框用的
			List<Category> parents = categoryService.getParents();
			model.addAttribute("parents", parents);
			return "category/edit";
		}
		return null;
	}
	
	/**
	 * 修改分类
	 * @author 	陈丽
	 * @Date 	2016年05月26日21:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/edit.do")
	public String edit(Category category, ModelMap model){
		Integer i = categoryService.edit(category);
		if(i>0){
			model.addAttribute("msg", "修改分类成功！");
			model.addAttribute("code", "success");
		}else{
			model.addAttribute("msg", "修改分类失败！");
			model.addAttribute("code", "error");
		}
		model.addAttribute("backLick", "/admin/category/getAllCategory.do");
		return "msg";
	}
	
	/**
	 * 组合查询分类
	 * @author 	陈丽
	 * @Date 	2016年05月26日22:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/queryCategoryByCategories.do")
	public String queryCategoryByCategories(Category category, ModelMap model){
		List<Category> categorys = categoryService.queryCategoryByCategories(category);
		model.addAttribute("parents", categorys);
		List<Category> parentss = categoryService.getAllCategory();
		model.addAttribute("parentss", parentss);
		model.addAttribute("category", category);
		return "category/list";
	}
	
	/**
	 * 删除一级分类
	 * @author 	陈丽
	 * @Date 	2016年05月26日22:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/deleteParent.do")
	public String deleteParent(String cid, ModelMap model){
		Integer i = categoryService.getChildrenCountByPid(cid);
		if(i > 0) {
			model.addAttribute("msg", "该分类下还有子分类，不能删除！");
			model.addAttribute("code", "error");
		} else {
			i = categoryService.delete(cid);
			if(i>0){
				model.addAttribute("msg", "删除分类成功！");
				model.addAttribute("code", "success");
			}else{
				model.addAttribute("msg", "删除分类失败！");
				model.addAttribute("code", "error");
			}
		}
		model.addAttribute("backLick", "/admin/category/getAllCategory.do");
		return "msg";
	}
	
	/**
	 * 删除二级分类
	 * @author 	陈丽
	 * @Date 	2016年05月26日15:00:08
	 * @throws 	
	 */
	@RequestMapping(value = "/deleteChild.do")
	public String deleteChild(String cid, ModelMap model){
		Integer i = categoryService.delete(cid);
		if(i>0){
			model.addAttribute("msg", "删除分类成功！");
			model.addAttribute("code", "success");
		}else{
			model.addAttribute("msg", "删除分类失败！");
			model.addAttribute("code", "error");
		}
		model.addAttribute("backLick", "/admin/category/getAllCategory.do");
		return "msg";
	}

}
