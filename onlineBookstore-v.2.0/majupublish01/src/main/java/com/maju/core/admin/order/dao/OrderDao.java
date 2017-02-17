package com.maju.core.admin.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.maju.core.admin.order.bean.Order;

public interface OrderDao {

	public Integer getOrderCount(Order order);

	public List<Order> getAllOrderWithPage(@Param("order")Order order, @Param("startRow")Integer startRow, @Param("pageSize")Integer pageSize);

	public Order getOrderById(String id);

	public Integer findStatus(String id);

	public Integer updateStatus(@Param("id")String id, @Param("status")Integer status);

}
