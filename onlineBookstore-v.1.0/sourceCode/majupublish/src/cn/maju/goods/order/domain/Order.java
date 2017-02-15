package cn.maju.goods.order.domain;

import java.util.List;

import cn.maju.goods.user.domain.User;

public class Order {
	private String oid;//订单id
	private String ordertime;//下单时间
	private double total;//订单总价
	private int status;//订单状态
	private String address;//收货地址
	private User owner;//订单所属者
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", total="
				+ total + ", status=" + status + ", address=" + address
				+ ", owner=" + owner + ", orderItemList=" + orderItemList + "]";
	}
	
	

}
