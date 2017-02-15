package cn.maju.goods.order.domain;

import cn.maju.goods.book.domain.Book;

public class OrderItem {
	private String orderItemId;//订单条目编号
	private int quantity;//一个条目包含的数量
	private double subtotal;//一个条目的小计
	private Book book;//这个条目的内容
	private Order order;//这个条目的所属订单
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
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", quantity="
				+ quantity + ", subtotal=" + subtotal + ", book=" + book
				+ ", order=" + order + "]";
	}
	
	

}
