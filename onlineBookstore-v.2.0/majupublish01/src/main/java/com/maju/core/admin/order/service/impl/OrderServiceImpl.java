package com.maju.core.admin.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maju.common.pagination.PageBean;
import com.maju.common.web.ConstantsCommon;
import com.maju.core.admin.order.bean.Order;
import com.maju.core.admin.order.bean.OrderItem;
import com.maju.core.admin.order.dao.OrderDao;
import com.maju.core.admin.order.dao.OrderItemDao;
import com.maju.core.admin.order.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Resource
	private OrderDao orderDao;
	@Resource
	private OrderItemDao orderItemDao;

	@Override
	@Transactional(readOnly=true)
	public PageBean<Order> getAllOrder(Order order, Integer startRow, Integer pageSize) {
		return finByCriteria(order,startRow,pageSize);
	}
	
	//根据条件查询订单
	private PageBean<Order> finByCriteria(Order order, Integer startRow, Integer pageSize){
		PageBean<Order> pb=new PageBean<Order>();
		
		List<Order> orders = orderDao.getAllOrderWithPage(order, startRow, pageSize);
		List<Order> dataList=new ArrayList<Order>();
		for(Order o:orders){
			dataList.add(loadOrderItem(o));
		}
		pb.setDataList(dataList);
		
		pb.setTr(orderDao.getOrderCount(order));
		pb.setPs(pageSize);
		return pb;
	}
	
	
	//为order加载orderItemList
	private Order loadOrderItem(Order order){
		List<OrderItem> orderItems = orderItemDao.getOrderItemByOid(order.getOid());
		for(OrderItem orderItem:orderItems){
			orderItem.setImage_b(ConstantsCommon.IMAGE_WEB_URL+orderItem.getImage_b());
		}
		order.setOrderItemList(orderItems);
		return order;
	}

	@Override
	public Order getOrderById(String id) {
		Order order = orderDao.getOrderById(id);
		return loadOrderItem(order);
	}

	@Override
	public Integer findStatus(String id) {
		return orderDao.findStatus(id);
	}

	@Override
	public Integer updateStatus(String id, Integer i) {
		return orderDao.updateStatus(id,i);
	}
	
}
