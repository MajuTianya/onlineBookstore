package cn.maju.goods.book.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang.StringUtils;
import org.hamcrest.core.Is;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import cn.maju.goods.book.domain.Book;
import cn.maju.goods.page.Expression;
import cn.maju.goods.page.PageBean;
import cn.maju.goods.page.PageConstants;

public class BookDao {
	static QueryRunner qr=new TxQueryRunner();

	/**
	 * 分类查询
	 * @param cid
	 * @param pc
	 * @return
	 * @throws SQLException 
	 */
	public PageBean<Book> findByCategory(String cid, int pc) throws SQLException {
		// TODO Auto-generated method stub
		List<Expression> exprList=new ArrayList<Expression>();
		exprList.add(new Expression("cid","=",cid));
		return findByCriteria(exprList,pc);
	}

	/**
	 * 通用查询方法
	 * @param exprList
	 * @param pc
	 * @return
	 * @throws SQLException 
	 */
	private static PageBean<Book> findByCriteria(List<Expression> exprList,
			int pc) throws SQLException {
		// TODO Auto-generated method stub
		
		/*
		 * 得到ps
		 * 得到tr
		 * 得到dataList
		 * 创建pageBean
		 */
		/*
		 * 1、得到ps
		 */
		int ps=PageConstants.BOOK_PAGE_SIZE;//得到每页的记录数
		
		/*
		 * 2、通过exprList生成whereSql-->目的：实现通用条件查询
		 * 		以" and "开头
		 * 		判断操作符是否为is null:否，追加"?"，并给expr设置value;否，就否。
		 */
		StringBuilder whereSql=new StringBuilder(" where 1=1");
		List<Object> params=new ArrayList<Object>();//如果在SQL中存在问号，要保存expr.getValue()在params中
		for(Expression expr:exprList){
			whereSql.append(" and "+expr.getName()+" "+expr.getOperater());
			if(!expr.getOperater().equalsIgnoreCase("is null")){
				if(expr.getOperater().trim().equalsIgnoreCase("in")){
					whereSql.append(" "+expr.getValue());
				}else{
					whereSql.append("?");
					params.add(expr.getValue());
				}
			}
		}
		/*
		 * 3、得到总记录数
		 * sql="select count(*) from t_book"+whereSql;
		 */
		String sql="select count(1) from t_book"+whereSql;
		Number cnt=(Number)qr.query(sql,new ScalarHandler(),params.toArray());
		int tr=cnt.intValue();
		/*
		 * 得到dataList
		 */
		sql="select * from t_book"+whereSql+" order by orderBy limit ?,?";
		params.add(ps*(pc-1));
		params.add(ps);
		List<Book> dataList=qr.query(sql, new BeanListHandler<Book>(Book.class),params.toArray());
		
		/*
		 * 创建pageBean
		 */
		PageBean<Book> pb=new PageBean<Book>();
		pb.setPc(pc);
		pb.setTr(tr);
		pb.setPs(ps);
		pb.setDataList(dataList);
		return pb;
	}

	/**
	 * 按图书名查询
	 * @param bname
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Book> findByBname(String bname, int pc) throws SQLException {
		// TODO Auto-generated method stub
		List<Expression> criteria=new ArrayList<Expression>();//这后面不能用List,中能用ArrayList
		criteria.add(new Expression("bname","like","%"+bname+"%"));
		return findByCriteria(criteria,pc);
	}

	/**
	 * 多条件组合查询
	 * @param book
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Book> findByConbination(Book book, int pc) throws SQLException {
		List<Expression> criteria=new ArrayList<Expression>();
		if(StringUtils.isNotBlank(book.getBname())){
			criteria.add(new Expression("bname","like","%"+book.getBname()+"%"));
		}
		if(StringUtils.isNotBlank(book.getAuthor())){
			criteria.add(new Expression("author","like","%"+book.getAuthor()+"%"));
		}
		if(StringUtils.isNotBlank(book.getPress())){
			criteria.add(new Expression("press","like","%"+book.getPress()+"%"));
		}
		if(StringUtils.isNotBlank(book.getCid())){
			criteria.add(new Expression("cid","in","('"+book.getCid()+"')"));
		}
		return findByCriteria(criteria,pc);
	}

	/**
	 * 按作者模糊查询
	 * @param Author
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Book> findByAuthor(String author, int pc) throws SQLException {
		// TODO Auto-generated method stub
		List<Expression> criteria=new ArrayList<Expression>();//这后面不能用List,中能用ArrayList
		criteria.add(new Expression("Author","like","%"+author+"%"));
		return findByCriteria(criteria,pc);
	}

	/**
	 * 按出版社模糊查询
	 * @param press
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Book> findByPress(String press, int pc) throws SQLException {
		// TODO Auto-generated method stub
		List<Expression> criteria=new ArrayList<Expression>();//这后面不能用List,中能用ArrayList
		criteria.add(new Expression("press","like","%"+press+"%"));
		return findByCriteria(criteria,pc);
	}

	/**
	 * 加载图书
	 * @param bid
	 * @return
	 * @throws SQLException
	 */
	public Book load(String bid) throws SQLException {
		// TODO Auto-generated method stub
		//建立sql语句
		String sql="select * from t_book where bid=?";
		//执行查询
		Map<String,Object> map=qr.query(sql,new MapHandler(),bid);
		//把结果集映射成Book
		Book book=CommonUtils.toBean(map, Book.class);
		return book;
	}

	public List<Book> findByTime() throws SQLException {
		// TODO Auto-generated method stub
		int size=PageConstants.INDEX_CONTENT_SIZE;
		String sql="select * from t_book order by publishtime limit ?,?";
		List<Book> dataList=qr.query(sql, new BeanListHandler<Book>(Book.class),0,size);
		return dataList;
	}

	public PageBean<Book> findBySearch(String searchValue, int pc) throws SQLException {
		// TODO Auto-generated method stub
		
		
		int ps=PageConstants.BOOK_PAGE_SIZE;//得到每页的记录数
		String sql="select count(*) from t_book where bname like ? or author like ? or press like ?";
		List<Object> params=new ArrayList<Object>();
		params.add("%"+searchValue+"%");
		params.add("%"+searchValue+"%");
		params.add("%"+searchValue+"%");
		Number cnt=(Number)qr.query(sql,new ScalarHandler(),params.toArray());
		int tr=cnt.intValue();
		/*
		 * 得到dataList
		 */
		sql="select * from t_book where bname like ? or author like ? or press like ? order by orderBy limit ?,?";
		params.add(ps*(pc-1));
		params.add(ps);
		List<Book> dataList=qr.query(sql, new BeanListHandler<Book>(Book.class),params.toArray());
		/*
		 * 创建pageBean
		 */
		PageBean<Book> pb=new PageBean<Book>();
		pb.setPc(pc);
		pb.setTr(tr);
		pb.setPs(ps);
		pb.setDataList(dataList);
		return pb;
	}

	//添加图书
	public void add(Book book) throws SQLException {
		String sql = "insert into t_book(bid,bname,author,price,currPrice," +
				"discount,press,publishtime,edition,pageNum,wordNum,printtime," +
				"booksize,paper,cid,image_w,image_b,bookList)" +
				" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {book.getBid(),book.getBname(),book.getAuthor(),
				book.getPrice(),book.getCurrPrice(),book.getDiscount(),
				book.getPress(),book.getPublishtime(),book.getEdition(),
				book.getPageNum(),book.getWordNum(),book.getPrinttime(),
				book.getBooksize(),book.getPaper(), book.getCategory().getCid(),
				book.getImage_w(),book.getImage_b(),book.getBookList()};
		qr.update(sql, params);
	}

	
	//返回当前分类下图书个数
	public int findBookCountByCategory(String cid) throws SQLException {
		String sql = "select count(1) from t_book where cid=?";
		Number cnt = (Number)qr.query(sql, new ScalarHandler(), cid);
		return cnt == null ? 0 : cnt.intValue();
	}

	
	//删除图书
	public void delete(String bid) throws SQLException {
		String sql = "delete from t_book where bid=?";
		qr.update(sql, bid);
	}

	
	//修改图书
	public void edit(Book book) throws SQLException {
		String sql = "update t_book set bname=?,author=?,price=?,currPrice=?," +
				"discount=?,press=?,publishtime=?,edition=?,pageNum=?,wordNum=?," +
				"printtime=?,booksize=?,paper=?,cid=?,image_w=?,image_b=?,bookList=? where bid=?";
		Object[] params = {book.getBname(),book.getAuthor(),
				book.getPrice(),book.getCurrPrice(),book.getDiscount(),
				book.getPress(),book.getPublishtime(),book.getEdition(),
				book.getPageNum(),book.getWordNum(),book.getPrinttime(),
				book.getBooksize(),book.getPaper(), 
				book.getCategory().getCid(),book.getImage_w(),book.getImage_b(),book.getBookList(),book.getBid()};
		qr.update(sql, params);
	}

	//查看所有
	public PageBean<Book> findAll(int pc) throws SQLException {
		// TODO Auto-generated method stub
		List<Expression> exprList = new ArrayList<Expression>();
		return findByCriteria(exprList, pc);
	}

}
