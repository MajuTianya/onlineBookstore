package cn.maju.goods.category.domain.Category;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Null;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import cn.maju.goods.category.dao.CategoryDao.Category;

public class CategoryDao {

	private QueryRunner qr=new TxQueryRunner();

	/**
	 * 查询所有分类
	 * @return
	 * @throws SQLException
	 */
	public List<Category> findAll() throws SQLException {
		// TODO Auto-generated method stub
		/*
		 * 1、获取pid=null的Category作为parents集合
		 * 2、遍历parents
		 * 		-->根据parent.cid可得到List<Category> children
		 * 		-->将children设置为parent的children
		 * 3、返回parents
		 */
		
		/*
		 * 1、获取pid=null的Category作为parents集合
		 */
		String sql="select * from t_category where pid is null order by orderBy";
		
		List<Map<String,Object>> parentsMap=qr.query(sql, new MapListHandler());
		
		/*
		 * 2、将List map转化为List Category-->List Category要新建
		 * 		逐一将map转化为Category
		 * 		将已转化的map加入List Category中,便得到了List<Category> parents
		 */
		List<Category> parents=toCategoryList(parentsMap);
		
		/*
		 * 3、循环遍历parents,通过parent.cid找到相应的children，并将它们赋给parent.chileren
		 */
		for(Category parent:parents){
			List<Category> children=findByParent(parent.getCid());
			parent.setChildren(children);
		}
		return parents;
	}
	
	public List<Category> findCategoryByCategories(Category category) throws SQLException {
		// TODO Auto-generated method stub
		/*
		 * 1、获取pid=null的Category作为parents集合
		 * 2、遍历parents
		 * 		-->根据parent.cid可得到List<Category> children
		 * 		-->将children设置为parent的children
		 * 3、返回parents
		 */
		
		/*
		 * 1、获取pid=null的Category作为parents集合//有漏洞
		 */
		//"select * from t_category where pid is null order by orderBy"
		StringBuilder sql=new StringBuilder("select * from t_category where 1=1 ");
		if(category != null){
		if(category.getCid()!=null && !category.getCid().equals("")){
			sql.append(" and cid='"+category.getCid()+"'");
		}
		if(category.getCname()!=null && !category.getCname().equals("")){
			sql.append(" and cname='"+category.getCname()+"'");
		}
		if(category.getParent() != null){
			if(category.getParent().getCid()!=null && !category.getParent().getCid().equals("")){
				sql.append(" and pid='"+category.getParent().getCid()+"'");
			}
		}else{
			sql.append(" and pid is null ");
		}
		}
		List<Map<String,Object>> parentsMap=qr.query(sql.toString(), new MapListHandler());
		
		/*
		 * 2、将List map转化为List Category-->List Category要新建
		 * 		逐一将map转化为Category
		 * 		将已转化的map加入List Category中,便得到了List<Category> parents
		 */
		List<Category> parents=toCategoryList(parentsMap);
		
		/*
		 * 3、循环遍历parents,通过parent.cid找到相应的children，并将它们赋给parent.chileren
		 */
		for(Category parent:parents){
			List<Category> children=findByParent(parent.getCid());
			parent.setChildren(children);
		}
		return parents;
	}

	/**
	 * findByParent(pid):通过一级分类找它对应的二级分类
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public List<Category> findByParent(String pid) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from t_category where pid=? order by orderBy";
		List<Map<String,Object>> children=qr.query(sql, new MapListHandler(),pid);
		
		/*
		 * 将List map 转化为List Category
		 */
		return toCategoryList(children);
	}

	/**
	 * toCategoryList(mapList):将mapList转化为categoryList
	 * @param mapList
	 * @return
	 */
	private List<Category> toCategoryList(List<Map<String, Object>> mapList) {
		// TODO Auto-generated method stub
		List<Category> categoryList=new ArrayList<Category>();
		for(Map<String, Object> map:mapList){
			Category c=CommonUtils.toBean(map, Category.class);
			String pid = (String) map.get("pid");
			if(pid!=null && !pid.equals("")){
				Category category = new Category();
				category.setCid(pid);
				c.setParent(category);
			}
			categoryList.add(c);
		}
		return categoryList;
	}

	//获取所有父分类，不带子分类
	public List<Category> findParents() throws SQLException {
		/*
		 * 1. 查询出所有一级分类
		 */
		String sql = "select * from t_category where pid is null order by orderBy";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
		
		return toCategoryList(mapList);
	}

	//添加分类
	public void add(Category category) throws SQLException {
		//"insert into t_category(cid,cname,pid,descr) values(?,?,?,?)";
		StringBuilder sql = new StringBuilder("insert into t_category");
		/*
		 * 因为一级分类，没有parent，而二级分类有！
		 * 我们这个方法，要兼容两次分类，所以需要判断
		 */
		if(category.getParent() != null && !category.getParent().equals("")) {
			if(category.getParent().getCid() != null && !category.getParent().getCid().equals("")){
				sql.append("(cid,cname,pid,descr) values(?,?,?,?)");
				Object[] params = {category.getCid(), category.getCname(), category.getParent().getCid(), category.getDescr()};
				qr.update(sql.toString(), params);
			}else {
				sql.append("(cid,cname,descr) values(?,?,?)");
				Object[] params = {category.getCid(), category.getCname(), category.getDescr()};
				qr.update(sql.toString(), params);
			}
		}
	}

	//加载分类
	public Category load(String cid) throws SQLException {
		String sql = "select * from t_category where cid=?";
		return toCategory(qr.query(sql, new MapHandler(), cid));
	}
	

	//加载分类:把一个Map中的数据映射到Category中
	private Category toCategory(Map<String,Object> map) {
		/*
		 * map {cid:xx, cname:xx, pid:xx, desc:xx, orderBy:xx}
		 * Category{cid:xx, cname:xx, parent:(cid=pid), desc:xx}
		 */
		Category category = CommonUtils.toBean(map, Category.class);
		String pid = (String)map.get("pid");// 如果是一级分类，那么pid是null
		if(pid != null) {//如果父分类ID不为空，
			/*
			 * 使用一个父分类对象来拦截pid
			 * 再把父分类设置给category
			 */
			Category parent = new Category();
			parent.setCid(pid);
			category.setParent(parent);
		}
		return category;
	}
	
	//修改分类
	//即可修改一级分类，也可修改二级分类
	public void edit(Category category) throws SQLException {
		String sql = "update t_category set cname=?, pid=?, descr=? where cid=?";
		String pid = null;
		if(category.getParent() != null && !category.getParent().equals("")) {
			pid = category.getParent().getCid();
		}
		Object[] params = {category.getCname(), pid, category.getDescr(), category.getCid()};
		qr.update(sql, params);
	}

	//查询指定父分类下子分类的个数
	public int findChildrenCountByParent(String pid) throws SQLException {
		String sql = "select count(*) from t_category where pid=?";
		Number cnt = (Number)qr.query(sql, new ScalarHandler(), pid);
		return cnt == null ? 0 : cnt.intValue();
	}

	
	//删除分类
	public void delete(String cid) throws SQLException{
		
		String sql = "delete from t_category where cid=?";
		qr.update(sql,cid);
	}
	
}
