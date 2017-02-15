package cn.maju.goods.author.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;
import cn.maju.goods.author.domain.Author;

public class AuthorDao {
	QueryRunner qr=new TxQueryRunner();

	public List<Author> findByIndex() throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from t_author where status in(?,?) order by status";
		List<Author> dataList=qr.query(sql, new BeanListHandler<Author>(Author.class),1,2);
		return dataList;
	}

}
