package cn.maju.goods.author.service;

import java.sql.SQLException;
import java.util.List;

import cn.maju.goods.author.dao.AuthorDao;
import cn.maju.goods.author.domain.Author;

public class AuthorService {
	AuthorDao authorDao=new AuthorDao();

	public List<Author> findByIndex() {
		// TODO Auto-generated method stub
		try {
			return authorDao.findByIndex();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
