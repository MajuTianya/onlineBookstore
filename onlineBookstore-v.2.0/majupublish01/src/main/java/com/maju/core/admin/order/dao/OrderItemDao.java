package com.maju.core.admin.order.dao;

import java.util.List;

import com.maju.core.admin.order.bean.Order;
import com.maju.core.admin.order.bean.OrderItem;

public interface OrderItemDao {

	public List<OrderItem> getOrderItemByOid(String oid);

}
