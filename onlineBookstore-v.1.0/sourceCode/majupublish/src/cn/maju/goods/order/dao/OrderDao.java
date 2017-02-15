package cn.maju.goods.order.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import cn.maju.goods.book.domain.Book;
import cn.maju.goods.order.domain.Order;
import cn.maju.goods.order.domain.OrderItem;
import cn.maju.goods.page.Expression;
import cn.maju.goods.page.PageBean;
import cn.maju.goods.page.PageConstants;

public class OrderDao {

	QueryRunner qr=new TxQueryRunner();

	/**
	 * 添加订单
	 * @param order
	 * @throws SQLException
	 */
	public void add(Order order) throws SQLException {
		// TODO Auto-generated method stub
		/*
		 * 1、插入订单
		 * 2、循环遍历订单的所有条目，让每个条目生成一个Object[] params
		 * 	 多个条目对应多个Object[][]
		 * 	执行批处理，插入订单条目
		 */
		
		String sql="insert into t_order values(?,?,?,?,?,?)";
		Object[] params={order.getOid(),order.getOrdertime(),order.getTotal(),order.getStatus(),order.getAddress(),order.getOwner().getUid()};
		qr.update(sql,params);
		
		sql="insert into t_orderitem values(?,?,?,?,?,?,?,?)";
		int len=order.getOrderItemList().size();
		Object[][] objs=new Object[len][];
		for(int i=0;i<len;i++){
			OrderItem orderItem=order.getOrderItemList().get(i);
			objs[i]=new Object[]{orderItem.getOrderItemId(),orderItem.getQuantity(),orderItem.getSubtotal(),orderItem.getBook().getBid(),
					orderItem.getBook().getBname(),orderItem.getBook().getCurrPrice(),orderItem.getBook().getImage_b(),orderItem.getOrder().getOid()};
		}
		qr.batch(sql, objs);
	}

	public PageBean<Order> myOrders(String uid, int pc) throws SQLException {
		// TODO Auto-generated method stub
		List<Expression> exprList=new ArrayList<Expression>();
		exprList.add(new Expression("uid","=",uid));
		return finByCriteria(exprList,pc);
	}

	/**
	 * 多条件查询订单
	 * @param exprList
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	private PageBean<Order> finByCriteria(List<Expression> exprList, int pc) throws SQLException {
		// TODO Auto-generated method stub
		/*
		 * 1、用exprList得到whereSql(sb)
		 * 2、从数据库得到tr
		 * 3、从pageConstant得到ps-->limit(ps*(pc-1),ps);
		 * 4、从数据库得到dataList
		 */
		StringBuilder sb=new StringBuilder(" where 1=1");
		List<Object> params=new ArrayList<Object>();
		for(Expression expr:exprList){
			sb.append(" and "+expr.getName()+" "+expr.getOperater()+"");
			if(!expr.getOperater().equalsIgnoreCase("is null")){
				sb.append("? ");
				params.add(expr.getValue());
			}
		}
		
		String sql="select count(*) from t_order"+sb;
		Number tr=(Number)qr.query(sql, new ScalarHandler(),params.toArray());
		
		int ps=PageConstants.ORDER_PAGE_SIZE;
		sql="select * from t_order "+sb+" order by ordertime desc limit ?,?";
		params.add(ps*(pc-1));
		params.add(ps);
		List<Order> beanList=qr.query(sql, new BeanListHandler<Order>(Order.class),params.toArray());
		List<Order> dataList=new ArrayList<Order>();
		for(Order order:beanList){
			dataList.add(loadOrderItem(order));
		}
		
		PageBean<Order> pb=new PageBean<Order>();
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr.intValue());
		pb.setDataList(dataList);
		return pb;
	}

	/**
	 * 为order加载orderItemList
	 * @param order
	 * @return 
	 * @throws SQLException
	 */
	private Order loadOrderItem(Order order) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from t_orderitem where oid=?";
		List<Map<String,Object>> mapList=qr.query(sql, new MapListHandler(),order.getOid());
		order.setOrderItemList(toOrderItemList(mapList));
		return order;
	}

	/**
	 * 将多个map转化为一个orderItemList
	 * @param mapList
	 * @return
	 */
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		// TODO Auto-generated method stub
		List<OrderItem> orderItemList=new ArrayList<OrderItem>();
		for(Map<String, Object> map:mapList){
			orderItemList.add(toOrderItem(map));
		}
		return orderItemList;
	}

	/**
	 * 将一个map转化为一个orderItem
	 * @param map
	 * @return
	 */
	private OrderItem toOrderItem(Map<String, Object> map) {
		// TODO Auto-generated method stub
		OrderItem orderItem=CommonUtils.toBean(map, OrderItem.class);
		Book book=CommonUtils.toBean(map, Book.class);
		orderItem.setBook(book);
		return orderItem;
	}

	/**
	 * 查看订单时加载详细内容
	 * @param oid
	 * @return
	 * @throws SQLException
	 */
	public Order load(String oid) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from t_order where oid=?";
		Order order=qr.query(sql, new BeanHandler<Order>(Order.class),oid);
		return loadOrderItem(order);
	}

	/**
	 * 通过oid查找订单状态
	 * @param oid
	 * @return
	 * @throws SQLException
	 */
	public int findStatusByOid(String oid) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select status from t_order where oid=?";
		Number cnt=(Number)qr.query(sql,new ScalarHandler(),oid);
		return cnt.intValue();
	}

	/**
	 * 更新订单状态
	 * @param oid
	 * @param status
	 * @throws SQLException
	 */
	public void updateStatus(String oid, int status) throws SQLException {
		// TODO Auto-generated method stub
		String sql="update t_order set status=? where oid=?";
		qr.update(sql,status,oid);
	}

	
	//查询所有
	public PageBean<Order> findAll(int pc) throws SQLException {
		List<Expression> exprList = new ArrayList<Expression>();
		return finByCriteria(exprList, pc);
	}

	//按状态查询
	public PageBean<Order> findByStatus(int status, int pc) throws SQLException {
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("status", "=", status + ""));
		return finByCriteria(exprList, pc);
	}

	//查询订单状态
	public int findStatus(String oid) throws SQLException {
		String sql = "select status from t_order where oid=?";
		Number number = (Number)qr.query(sql, new ScalarHandler(), oid);
		return number.intValue();
	}
}
