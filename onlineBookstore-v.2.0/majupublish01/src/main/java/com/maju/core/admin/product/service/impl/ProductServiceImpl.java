package com.maju.core.admin.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maju.common.pagination.PageBean;
import com.maju.common.web.ConstantsCommon;
import com.maju.core.admin.product.bean.Product;
import com.maju.core.admin.product.dao.ProductDao;
import com.maju.core.admin.product.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Resource
	private ProductDao productDao;

	@Override
	public PageBean<Product> getAllProduct(Product product, int startRow,
			int pageSize) {
		PageBean<Product> pb=new PageBean<Product>();
		List<Product> products = productDao.getAllProductWithPage(product, startRow, pageSize);
		pb.setDataList(products);
		pb.setTr(productDao.getProductCount(product));
		pb.setPs(pageSize);
		return pb;
	}

	@Override
	public Integer addProduct(Product product) {
		return productDao.addProduct(product);
	}

	@Override
	public Product getProductById(String id) {
		Product product = productDao.getProductById(id);
		product.setImage_b(ConstantsCommon.IMAGE_WEB_URL+product.getImage_b());
		product.setImage_w(ConstantsCommon.IMAGE_WEB_URL+product.getImage_w());
		return product;
	}

	@Override
	public Integer updateProduct(Product product) {
		return productDao.updateProduct(product);
	}

	@Override
	public Integer deleteProductByKey(String id) {
		return productDao.deleteProductByKey(id);
	}

	@Override
	public Integer deleteProductByKeys(String[] ids) {
		return productDao.deleteProductByKeys(ids);
	}

}
