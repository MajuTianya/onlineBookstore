package com.maju.core.admin.order.service;

import com.maju.common.pagination.PageBean;
import com.maju.core.admin.order.bean.Order;

public interface OrderService {

	public PageBean<Order> getAllOrder(Order order, Integer startRow, Integer pageSize);

	public Order getOrderById(String id);

	public Integer findStatus(String id);

	public Integer updateStatus(String id, Integer status);

}
