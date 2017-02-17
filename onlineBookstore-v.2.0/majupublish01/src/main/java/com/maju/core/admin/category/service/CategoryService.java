package com.maju.core.admin.category.service;

import java.util.List;

import com.maju.core.admin.category.bean.Category;

public interface CategoryService {

	public List<Category> getAllCategory();

	public List<Category> queryCategoryByCategories(Category category);

	public List<Category> getParents();

	public Integer add(Category category);

	public Category getCategoryByCid(String cid);

	public Integer edit(Category category);

	public Integer getChildrenCountByPid(String pid);

	public Integer delete(String cid);

	public List<Category> getChildrenByPid(String pid);

}
