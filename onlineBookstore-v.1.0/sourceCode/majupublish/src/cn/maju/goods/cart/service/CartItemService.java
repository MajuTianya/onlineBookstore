package cn.maju.goods.cart.service;

import java.sql.SQLException;
import java.util.List;

import cn.maju.goods.cart.dao.CartItemDao;
import cn.maju.goods.cart.domain.CartItem;

public class CartItemService {

	CartItemDao cartItemDao=new CartItemDao();

	/**
	 * 更新quantity
	 * @Author tsxy2012ChenLi
	 * @param cartItemId
	 * @param quantity
	 * @return
	 */
	public CartItem updateQuantity(String cartItemId, int quantity) {
		// TODO Auto-generated method stub
		try {
			cartItemDao.updateQuantity(cartItemId,quantity);
			return cartItemDao.findByCartItemId(cartItemId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/**
	 * 显示购物车信息
	 * @param uid
	 * @return
	 */
	public List<CartItem> myCart(String uid) {
		// TODO Auto-generated method stub
		try {
			return cartItemDao.findByUid(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加购物车条目
	 * @param cartItem
	 */
	public void add(CartItem cartItem) {
		// TODO Auto-generated method stub
		try {
			cartItemDao.add(cartItem);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按bid和uid查询购物车条目
	 * @param bid
	 * @param uid
	 * @return
	 */
	public CartItem findByBidAndUid(String bid, String uid) {
		// TODO Auto-generated method stub
		try {
			return cartItemDao.findByBidAndUid(bid,uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public void batchDelete(String cartItemIds) {
		// TODO Auto-generated method stub
		try {
			cartItemDao.batchDelete(cartItemIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public List<CartItem> loadCartItem(String cartItemIds) {
		// TODO Auto-generated method stub
		try {
			return cartItemDao.loadCartItem(cartItemIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}

	
}
