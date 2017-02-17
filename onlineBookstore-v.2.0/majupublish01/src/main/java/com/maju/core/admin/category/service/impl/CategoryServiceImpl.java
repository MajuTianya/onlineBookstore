package com.maju.core.admin.category.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maju.core.admin.category.bean.Category;
import com.maju.core.admin.category.dao.CategoryDao;
import com.maju.core.admin.category.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Resource
	private CategoryDao categoryDao;

	@Override
	@Transactional(readOnly=true)
	public List<Category> getAllCategory() {
		List<Category> categorys = categoryDao.getCategoryOne();
		return addchildren(categorys);
	}
	
	//装载parent
	private List<Category> addParent(List<Category> categorys){
		for(Category category:categorys){
			if(null!=category && StringUtils.isNotBlank(category.getPid())){
				String pid = category.getPid();
				category.setParent(categoryDao.getCategoryByCid(pid));
			}
		}
		return categorys;
	}
	
	//装载children
	private List<Category> addchildren(List<Category> categorys){
		for(Category category:categorys){
			if(null!=category && StringUtils.isNotBlank(category.getCid())){
				String cid = category.getCid();
				category.setChildren(categoryDao.getChildrenByPid(cid));;
			}
		}
		return categorys;
	}

	@Override
	public List<Category> queryCategoryByCategories(Category category) {
		List<Category> categorys = categoryDao.queryCategoryByCategories(category);
		return addchildren(addParent(categorys));
	}

	@Override
	public List<Category> getParents() {
		return categoryDao.getCategoryOne();
	}

	@Override
	public Integer add(Category category) {
		return categoryDao.add(category);
	}

	@Override
	public Category getCategoryByCid(String cid) {
		return categoryDao.getCategoryByCid(cid);
	}

	@Override
	public Integer edit(Category category) {
		return categoryDao.edit(category);
	}

	@Override
	public Integer getChildrenCountByPid(String pid) {
		return categoryDao.getChildrenCountByPid(pid);
	}

	@Override
	public Integer delete(String cid) {
		return categoryDao.delete(cid);
	}

	@Override
	public List<Category> getChildrenByPid(String pid) {
		return categoryDao.getChildrenByPid(pid);
	}

}
