package cn.maju.goods.cart.domain;

import java.math.BigDecimal;

import cn.itcast.commons.CommonUtils;
import cn.maju.goods.book.domain.Book;
import cn.maju.goods.user.domain.User;

public class CartItem {
	private String cartItemId;
	private int quantity;
	private Book book;
	private User owner;
	//bookitem的成员用到了两个类对象，所以得到的应该是个map结果集，然后将map结果集分别映射成book和user对象，并把他们作为cartitem的对象。
	/**
	 * 返回小计
	 * @Author tsxy2012ChenLi
	 * @return
	 */
	public double getSubtotal(){
		BigDecimal v1=new BigDecimal(Double.toString(book.getCurrPrice()));//第一个大数是购物车一个条目（已本书）的当前价
		BigDecimal v2=new BigDecimal(Double.toString(quantity));//第二个大数是购物车一个条目所包含的书数目
		return v1.multiply(v2).doubleValue();
	}

	public String getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "CartItem [cartItemId=" + cartItemId + ", quantity=" + quantity
				+ ", book=" + book + ", owner=" + owner + "]";
	}

	
}
