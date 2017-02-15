package cn.maju.goods.cart.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import cn.maju.goods.book.domain.Book;
import cn.maju.goods.cart.domain.CartItem;
import cn.maju.goods.user.domain.User;

public class CartItemDao {

	QueryRunner qr=new TxQueryRunner();

	/**
	 * 更新购物车里的quantity
	 * @Author tsxy2012ChenLi
	 * @param cartItem
	 * @param quantity
	 * @throws SQLException
	 */
	public void updateQuantity(String cartItemId, int quantity) throws SQLException {
		// TODO Auto-generated method stub
		String sql="update t_cartitem set quantity=? where cartItemId=?";
		qr.update(sql,quantity,cartItemId);
	}

	/**
	 * 按cartItemId找书的单价
	 * @param cartItemId
	 * @return
	 * @throws SQLException
	 */
	public CartItem findByCartItemId(String cartItemId) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from t_cartitem c,t_book b where c.bid=b.bid and cartItemId=?";
		Map<String,Object> map=qr.query(sql, new MapHandler(),cartItemId);
		return toCartItem(map);
	}

	/**
	 * 将数据集map转换成CartItem
	 * @param map
	 * @return
	 */
	private CartItem toCartItem(Map<String, Object> map) {
		// TODO Auto-generated method stub
		if(map==null||map.size()==0)return null;
		//把map封装成cartItem
		CartItem cartItem=CommonUtils.toBean(map, CartItem.class);
		//把map封装成book
		Book book=CommonUtils.toBean(map, Book.class);
		//把map封装成user
		User owner=CommonUtils.toBean(map, User.class);
		//把book,user设为cartItem成员，返回cartItem对象
		cartItem.setBook(book);
		cartItem.setOwner(owner);
		return cartItem;
	}

	/**
	 * 按用户id查询购物车列表
	 * @Author tsxy2012ChenLi
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public List<CartItem> findByUid(String uid) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select *from t_cartitem c,t_book b where c.bid=b.bid and uid=?";
		List<Map<String,Object>> mapList=qr.query(sql, new MapListHandler(),uid);
		return toCartItemList(mapList);
	}

	/**
	 * 转换成购物车条目列表
	 * @Author tsxy2012ChenLi
	 * @param mapList
	 * @return
	 */
	private List<CartItem> toCartItemList(List<Map<String, Object>> mapList) {
		// TODO Auto-generated method stub
		List<CartItem> cartItemList=new ArrayList<CartItem>();
		for(Map<String,Object> map:mapList){
			CartItem cartItem=toCartItem(map);
			cartItemList.add(cartItem);
		}
		return cartItemList;
	}

	/**
	 * 给购物车添加新条目
	 * @param cartItem
	 * @throws SQLException
	 */
	public void add(CartItem cartItem) throws SQLException {
		// TODO Auto-generated method stub
		String sql="insert into t_cartitem(cartItemId,quantity,bid,uid) values(?,?,?,?)";
		Object[] params={cartItem.getCartItemId(),cartItem.getQuantity(),cartItem.getBook().getBid(),cartItem.getOwner().getUid()};
		qr.update(sql,params);
		
	}

	/**
	 * 按bid和uid查询购物车条目
	 * @param bid
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public CartItem findByBidAndUid(String bid, String uid) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from t_cartitem where bid=? and uid=?";
		return qr.query(sql,new BeanHandler<CartItem>(CartItem.class),bid,uid);
	}

	/**
	 * 按cartItemIds批量删除
	 * @param cartItemIds
	 * @throws SQLException
	 */
	public void batchDelete(String cartItemIds) throws SQLException {
		// TODO Auto-generated method stub
		Object[] params=cartItemIds.split(",");
		String whereSql=toWhereSql(params.length);
		String sql="delete from t_cartitem where"+whereSql;
		qr.update(sql,params);
	}

	/**
	 * 构造选择参数sql语句
	 * @param length
	 * @return
	 */
	private String toWhereSql(int length) {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder(" cartItemId in(");
		for(int i=0;i<length;i++){
			sb.append("?");
			if(i<length-1){
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	} 

	public List<CartItem> loadCartItem(String cartItemIds) throws SQLException {
		// TODO Auto-generated method stub
		Object[] params=cartItemIds.split(",");
		String whereSql=toWhereSql(params.length);
		String sql="select * from t_cartitem c,t_book b where c.bid=b.bid and "+whereSql;
		List<Map<String, Object>> mapList=qr.query(sql,new MapListHandler(),params);
		return toCartItemList(mapList);
	}
	
}
