package cn.maju.goods.user.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.maju.goods.user.dao.UserDao;
import cn.maju.goods.user.domain.User;
import cn.maju.goods.user.service.exception.UserException;

/**
 * 用户模块业务层
 * @Author Administrator
 * */
public class UserService {
	private UserDao userDao=new UserDao();
	
	/**
	 * 激活功能
	 * @param code
	 * @throws UserException 
	 */
	public void activation(String code) throws UserException{
		/*
		 * 1、通过激活码查询用户
		 * 2、如果User为null,说明无效的激活码，抛出异常，异常信息（无效激活码）;
		 * 3、查看用户状态是否为true，如果为true，抛出异常，给出异常信息（请不要二次激活）
		 * 4、修改用户状态为true
		 */
		User user;
		try {
			user = userDao.findByCode(code);
			if(user==null) throw new UserException("无效的激活码！");
			if(user.getStatus()) throw new UserException("您已经激活了，不要二次激活！");
			userDao.updateStatus(user.getUid(), true);//修改状态
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 校验指定登录名是否存在
	 * @param loginname
	 * @return
	 */
	public boolean validateLoginname(String loginname) {
		// TODO Auto-generated method stub
		try {
			return userDao.validateloginLoginname(loginname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/**
	 * 校验email是否注册
	 * @param email
	 * @return
	 */
	public boolean validateEmail(String email) {
		// TODO Auto-generated method stub
		try {
			return userDao.validateEmail(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/**
	 * 注册功能
	 * @param user
	 * @throws MessagingException
	 */
	public void regist(User user) throws MessagingException {
		// TODO Auto-generated method stub
		try{
		/*
		 * 1、对user进行数据补全
		 */
		user.setUid(CommonUtils.uuid());
		user.setActivationCode(CommonUtils.uuid()+CommonUtils.uuid());
		user.setStatus(false);
		
		/*
		 * 向数据库添加数据
		 */
		userDao.add(user);
		
		/*
		 * 3、向用户注册邮件地址发送邮件
		 */
		//获取模板中的数据
		Properties props=new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		String host=props.getProperty("host");//获取邮件服务器地址
		String username=props.getProperty("username");//获取用户名
		String password=props.getProperty("password");//获取密码
		String from=props.getProperty("from");//获取发件人地址
		String to=user.getEmail();//获取收件人地址
		String subject=props.getProperty("subject");//获取主题
		//获取模板的内容，替换其中的激活码
		String content=MessageFormat.format(props.getProperty("content"), user.getActivationCode());
		
		//发送邮件
		Session session=MailUtils.createSession(host, username, password);
		Mail mail=new Mail(from,to,subject,content);
		MailUtils.send(session, mail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 校验指定用户名和密码是否存在用户
	 * @param userForm
	 * @return
	 */
	public User login(User userForm) {
		// TODO Auto-generated method stub
		try {
			return userDao.findByLoginnameAndLoginpass(userForm.getLoginname(),userForm.getLoginpass());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 修改密码功能
	 * @param uid
	 * @param loginpass
	 * @param newLoginpass
	 * @throws UserException 
	 */
	public void updateLoginpass(String uid, String loginpass,
			String newloginpass) throws UserException {
		// TODO Auto-generated method stub
		try {
			boolean bool=userDao.findByUidAndLoginpass(uid,loginpass);
			if(!bool){
				throw new UserException("原密码错误！");
			}
			userDao.updateLoginpass(loginpass,newloginpass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

}
