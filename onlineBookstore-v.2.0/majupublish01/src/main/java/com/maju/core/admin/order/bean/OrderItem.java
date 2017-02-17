package com.maju.core.admin.order.bean;

import com.maju.core.admin.product.bean.Product;

public class OrderItem {
	private String orderItemId;//订单条目编号
	private int quantity;//一个条目包含的数量
	private double subtotal;//一个条目的小计
	private String bid;//商品id
	private String bname;//商品名称
	private String currPrice;//当前价
	private String image_b;//小图
	private Product product;//这个条目的内容
	private String oid;//订单id
	private Order order;//这个条目的所属订单
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getCurrPrice() {
		return currPrice;
	}
	public void setCurrPrice(String currPrice) {
		this.currPrice = currPrice;
	}
	public String getImage_b() {
		return image_b;
	}
	public void setImage_b(String image_b) {
		this.image_b = image_b;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
}
