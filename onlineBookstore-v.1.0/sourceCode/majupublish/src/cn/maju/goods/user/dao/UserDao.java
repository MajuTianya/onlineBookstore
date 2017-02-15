package cn.maju.goods.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import cn.maju.goods.user.domain.User;

/**
 * 用户模块的持久层
 * @Author Administrator
 * */
public class UserDao {
	private QueryRunner qr=new TxQueryRunner();
	
	/**
	 * 通过激活码查询用户
	 * @param code
	 * @return
	 * @throws SQLException 
	 */
	public User findByCode(String code) throws SQLException{
		String sql="select * from t_user where activationCode=?";
		return qr.query(sql,new BeanHandler<User>(User.class),code);
	}
	
	/**
	 * 修改用户激活状态
	 * @param uid
	 * @param status
	 * @throws SQLException
	 */
	public void updateStatus(String uid,boolean status) throws SQLException{
		String sql="update t_user set status=? where uid=?";
		qr.update(sql,status,uid);
	}
	/**
	 * 校验指定登录名是否存在
	 * @param loginname
	 * @return
	 * @throws SQLException 
	 */
	public boolean validateloginLoginname(String loginname) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select count(1) from t_user where loginname=?";
		Number cnt=(Number)qr.query(sql,new ScalarHandler(),loginname);
		return cnt==null?false:cnt.intValue()>0;
	}

	public boolean validateEmail(String email) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select count(1) from t_user where email=?";
		Number cnt=(Number)qr.query(sql, new ScalarHandler(),email);
		return cnt==null?false:cnt.intValue()>0;
	}
	/**
	 * 添加用户
	 * @param user
	 * @throws SQLException
	 */
	public void add(User user) throws SQLException {
		// TODO Auto-generated method stub
		String sql="insert into t_user values(?,?,?,?,?,?)";
		user.setUid(CommonUtils.uuid());
		Object[] params={user.getUid(),user.getLoginname(),user.getLoginpass(),
				user.getEmail(),user.getStatus(),user.getActivationCode()};
		qr.update(sql,params);
	}

	/**
	 * 通过指定用户名和密码查找该用户
	 * @param loginname
	 * @param loginpass
	 * @return
	 * @throws SQLException 
	 */
	public User findByLoginnameAndLoginpass(String loginname,
			String loginpass) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from t_user where loginname=? and loginpass=?";
		return qr.query(sql,new BeanHandler<User>(User.class),loginname,loginpass);
		
	}

	/**
	 * 通过指定已登录用户id和原密码查找user对象
	 * @param uid
	 * @param loginpass
	 * @return
	 * @throws SQLException 
	 */
	public boolean findByUidAndLoginpass(String uid, String loginpass) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select count(*) from t_user where uid=? and loginpass=?";
		Number cnt=(Number)qr.query(sql, new ScalarHandler(),uid,loginpass);
		return cnt==null?false:cnt.intValue()>0;
	}

	/**
	 * 通过指定原密码和新密码修改原密码
	 * @param loginpass
	 * @param newloginpass
	 * @throws SQLException 
	 */
	public void updateLoginpass(String loginpass, String newloginpass) throws SQLException {
		// TODO Auto-generated method stub
		String sql="update t_user set loginpass=? where loginpass=?";
		qr.update(sql,newloginpass,loginpass);
	}

}
