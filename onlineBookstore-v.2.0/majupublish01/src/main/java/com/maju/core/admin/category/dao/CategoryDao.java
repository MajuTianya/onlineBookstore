package com.maju.core.admin.category.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.maju.core.admin.category.bean.Category;

public interface CategoryDao {

	public List<Category> getCategoryOne();

	public Category getCategoryByCid(@Param("cid")String cid);

	public List<Category> getChildrenByPid(@Param("pid")String pid);

	public List<Category> queryCategoryByCategories(Category category);

	public Integer add(Category category);

	public Integer edit(Category category);

	public Integer getChildrenCountByPid(@Param("pid")String pid);

	public Integer delete(String cid);

}
