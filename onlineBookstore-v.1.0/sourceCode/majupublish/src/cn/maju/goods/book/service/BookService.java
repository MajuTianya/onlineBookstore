package cn.maju.goods.book.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.jdbc.JdbcUtils;
import cn.maju.goods.book.dao.BookDao;
import cn.maju.goods.book.domain.Book;
import cn.maju.goods.order.domain.Order;
import cn.maju.goods.page.PageBean;

public class BookService {
	BookDao bookDao=new BookDao();

	/**
	 * 分类查找
	 * @param cid
	 * @param pc
	 * @return
	 */
	public PageBean<Book> findByCategory(String cid, int pc) {
		// TODO Auto-generated method stub
		
		try {
			return bookDao.findByCategory(cid,pc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按图书名查询
	 * @param bname
	 * @param pc
	 * @return
	 */
	public PageBean<Book> findByBname(String bname, int pc) {
		// TODO Auto-generated method stub
		try {
			return bookDao.findByBname(bname,pc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/**
	 * 多条件组合模糊查询
	 * @param book
	 * @param pc
	 * @return
	 */
	public PageBean<Book> findByCombination(Book book, int pc) {
		// TODO Auto-generated method stub
		try {
			return bookDao.findByConbination(book,pc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按作者模糊查询
	 * @param Author
	 * @param pc
	 * @return
	 */
	public PageBean<Book> findByAuthor(String author, int pc) {
		// TODO Auto-generated method stub
		try {
			return bookDao.findByAuthor(author,pc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按出版社模糊查询
	 * @param press
	 * @param pc
	 * @return
	 */
	public PageBean<Book> findByPress(String press, int pc) {
		// TODO Auto-generated method stub
		try {
			return bookDao.findByPress(press,pc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	/**
	 * 加载图书
	 * @param bid
	 * @return
	 */
	public Book load(String bid) {
		// TODO Auto-generated method stub
		try {
			return bookDao.load(bid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public List<Book> findByTime() {
		// TODO Auto-generated method stub
		try {
			return bookDao.findByTime();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	public PageBean<Book> findBySearch(String searchValue, int pc) {
		// TODO Auto-generated method stub
		try {
			return bookDao.findBySearch(searchValue,pc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	//添加图书
	public void add(Book book) {
		// TODO Auto-generated method stub
		try {
			bookDao.add(book);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	//返回当前分类下图书个数
	public int findBookCountByCategory(String cid) {
		try {
			return bookDao.findBookCountByCategory(cid);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	//删除图书
	public void delete(String bid) {
		try {
			bookDao.delete(bid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	//修改图书
	public void edit(Book book) {
		try {
			bookDao.edit(book);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public PageBean<Book> findAll(int pc) {
		// TODO Auto-generated method stub
		try {
			JdbcUtils.beginTransaction();
			PageBean<Book> pb = bookDao.findAll(pc);
			JdbcUtils.commitTransaction();
			return pb;
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {}
			throw new RuntimeException(e);
		}
	}

	
}
