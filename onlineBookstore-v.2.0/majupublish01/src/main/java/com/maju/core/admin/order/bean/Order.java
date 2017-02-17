package com.maju.core.admin.order.bean;

import java.util.List;

import com.maju.core.admin.admin.bean.Admin;

public class Order {
	private String oid;//订单id
	private String ordertime;//下单时间
	private double total;//订单总价
	private Integer status;//订单状态
	private String address;//收货地址
	private String uid;//用户id
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	private Admin owner;//订单所属者
	private List<OrderItem> orderItemList;//订单条目集合
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Admin getOwner() {
		return owner;
	}
	public void setOwner(Admin owner) {
		this.owner = owner;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

}
