package com.maju.core.admin.product.service;

import com.maju.common.pagination.PageBean;
import com.maju.core.admin.product.bean.Product;

public interface ProductService {

	public PageBean<Product> getAllProduct(Product product, int startRow, int pageSize);

	public Integer addProduct(Product product);

	public Product getProductById(String id);

	public Integer updateProduct(Product product);

	public Integer deleteProductByKey(String id);

	public Integer deleteProductByKeys(String[] ids);

}
