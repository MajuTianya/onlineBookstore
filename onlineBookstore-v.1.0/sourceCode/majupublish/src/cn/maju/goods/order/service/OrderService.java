package cn.maju.goods.order.service;

import java.sql.SQLException;

import cn.itcast.jdbc.JdbcUtils;
import cn.maju.goods.order.dao.OrderDao;
import cn.maju.goods.order.domain.Order;
import cn.maju.goods.page.PageBean;

public class OrderService {

	OrderDao orderDao=new OrderDao();

	/**
	 * 创建订单
	 * @param order
	 */
	public void createOrder(Order order) {
		// TODO Auto-generated method stub
		try {
			JdbcUtils.beginTransaction();
			orderDao.add(order);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * 按用户uid查询
	 * @param uid
	 * @param pc
	 * @return
	 */
	public PageBean<Order> myOrders(String uid, int pc) {
		// TODO Auto-generated method stub
		try {
			return orderDao.myOrders(uid,pc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查看时加载订单纤细内容
	 * @param oid
	 * @return
	 */
	public Order load(String oid) {
		// TODO Auto-generated method stub
		try {
			return orderDao.load(oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过oid查看订单状态
	 * @param oid
	 * @return
	 */
	public int findStatusByOid(String oid) {
		// TODO Auto-generated method stub
		try {
			return orderDao.findStatusByOid(oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/**
	 * 更新订单状态
	 * @param oid
	 * @param status
	 */
	public void updateStatus(String oid, int status) {
		// TODO Auto-generated method stub
		try {
			JdbcUtils.beginTransaction();
			orderDao.updateStatus(oid,status);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try{
				JdbcUtils.rollbackTransaction();
			}catch(SQLException e1){
				throw new RuntimeException(e1);
			}
		}
	}

	
	//查询所有
	public PageBean<Order> findAll(int pc) {
		try {
			JdbcUtils.beginTransaction();
			PageBean<Order> pb = orderDao.findAll(pc);
			JdbcUtils.commitTransaction();
			return pb;
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {}
			throw new RuntimeException(e);
		}
	}

	//按状态查询
	public PageBean<Order> findByStatus(int status, int pc) {
		try {
			JdbcUtils.beginTransaction();
			PageBean<Order> pb = orderDao.findByStatus(status, pc);
			JdbcUtils.commitTransaction();
			return pb;
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {}
			throw new RuntimeException(e);
		}
	}

	
	//查询订单状态
	public int findStatus(String oid) {
		try {
			return orderDao.findStatus(oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
