package com.maju.core.admin.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.maju.core.admin.product.bean.Product;

public interface ProductDao {

	public List<Product> getAllProductWithPage(@Param("product")Product product, @Param("startRow")int startRow, @Param("pageSize")int pageSize);

	public int getProductCount(Product product);

	public Integer addProduct(Product product);

	public Product getProductById(String id);

	public Integer updateProduct(Product product);

	public Integer deleteProductByKey(String id);

	public Integer deleteProductByKeys(String[] ids);

}
