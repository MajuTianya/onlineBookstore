package cn.maju.goods.book.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.maju.goods.author.domain.Author;
import cn.maju.goods.author.service.AuthorService;
import cn.maju.goods.book.domain.Book;
import cn.maju.goods.book.service.BookService;
import cn.maju.goods.page.PageBean;

public class BookServlet extends BaseServlet {
	
	private BookService bookService=new BookService();
	private AuthorService authorService=new AuthorService();

	/**
	 * 按分类查询
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1、获取当前的页码
		 * 		获取pc
		 * 		判断pc是否为空-->是,pc=1;否，是否能强制转换成整数-->是返回pc，否,抛出异常,返回，pc=1;
		 * 2、通过(cid,pc)使用BookService查询，（属于根据条件查询）得到PageBean
		 * 		从页面的得到二级分类(cid)-->从数据库中获取totalrols(ts)
		 * 		从系统中得到pagesize(ps),从页面得到pageCode(pc),计算limit(ps*(pc-1),ps),据此查询出mapBookList,逐个将mapBook转化为book和category,将category设为book的二级分类,将该book加入到该bookList
		 * 		创建PageBean,为其设置(pc,tr,ps,dataList),得到PageBean
		 * 3、获取url，设置PageBean
		 * 		url=request.getRequestURI()+"?"+request.getQueryString();
		 * 			-->得到的"/goods/jsps/BookServlet.java"+"?"+"method=findByCategory&cid=child.cid"(从left.jsp触发)或
		 * 			-->"/goods/jsps/BookServlet"+"?"+"method=findByCategory&cid=child.cid&pc='${pc}'"(从page.jsp触发)
		 * 		url是否包含了pc参数-->否，返回url，即url="/goods/jsps/BookServlet?method=findByCategory&cid=child.cid"；是，url中pc参数是不是最后的参数-->是:返回url;否，跳过pc得到包含其他参数url，并返回。
		 *		设置pageBean的url
		 * 4、把PageBean保存到request，转发到/jsps/book/list.jsp
		 */
		
		/*
		 * 1、获取当前的页码
		 */
		int pc=getPc(request);
		
		/*
		 * 2、通过(cid,pc)使用BookService.findByCategory()，返回pageBean(未设置url)
		 */
		String cid=request.getParameter("cid");
		PageBean<Book> pb=bookService.findByCategory(cid,pc);
		
		/*
		 * 3、获得url
		 */
		String url=getUrl(request);
		
		/*
		 * 4、位pageBean设置url
		 */
		pb.setUrl(url);
		
		/*
		 * 将pb保存到request中，并转发到list.jsp中
		 */
		request.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";
	}
	
	/*
	 * 获取请求的url，但去除pc参数
	 */
	private String getUrl(HttpServletRequest request) {
		String url=request.getRequestURI()+"?"+request.getQueryString();
		//看看url里有没有pc参数
		int fromIndex=url.lastIndexOf("&pc=");
		if(fromIndex==-1){
			//没有pc参数，直接返回url
			return url;
		}
		//看看pc参数后面是否还有参数
		int toIndex=url.indexOf("&",fromIndex+1);
		if(toIndex==-1){
			//pc参数后面没有参数
			return url.substring(0,fromIndex);
		}else {
			//pc参数后面还有参数
			return url.substring(0,fromIndex)+url.substring(toIndex);
		}
		
	}

	/*
	 * 获取当前pc
	 */
	private int getPc(HttpServletRequest request) {
		String pageCode=request.getParameter("pc");
		if(pageCode!=null&&!pageCode.trim().isEmpty()) {
			try{
				return Integer.parseInt(pageCode);
			}catch(RuntimeException e){}
		} 
		return 1;
	}
	
	/**
	 * 按图书名查询
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByBname(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 按图书名查询
		 * 1、获取当前页pc
		 * 2、通过bookService.findByBname(bname,pc),获取pageBean(未设置url)
		 * 3、获取url以被转发页面调用url
		 * 4、将pageBean保存到request中，并转发回list.jsp
		 */
		int pc=getPc(request);
		
		String bname=request.getParameter("bname");
		PageBean<Book> pb=bookService.findByBname(bname,pc);
		
		String url=getUrl(request);
		pb.setUrl(url);
		
		request.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";
	}
	
	/**
	 * 多条件组合查询
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCombination(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 按组合查询
		 * 1、获取当前页pc
		 * 2、通过bookService.findByCombination(book,pc),获取pageBean(未设置url)
		 * 3、获取url以被转发页面调用url
		 * 4、将pageBean保存到request中，并转发回list.jsp
		 */
		int pc=getPc(request);
		
		Book book=CommonUtils.toBean(request.getParameterMap(), Book.class);
		PageBean<Book> pb=bookService.findByCombination(book,pc);
		
		String url=getUrl(request);
		pb.setUrl(url);
		
		request.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";
	}
	
	/**
	 * 按作者模糊查询
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByAuthor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 按组合查询
		 * 1、获取当前页pc
		 * 2、通过bookService.findByCombination(book,pc),获取pageBean(未设置url)
		 * 3、获取url以被转发页面调用url
		 * 4、将pageBean保存到request中，并转发回list.jsp
		 */
		int pc=getPc(request);
		
		String author=request.getParameter("Author");
		PageBean<Book> pb=bookService.findByAuthor(author,pc);
		
		String url=getUrl(request);
		pb.setUrl(url);
		
		request.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";
	}
	
	/**
	 * 按出版社模糊查询
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByPress(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 按组合查询
		 * 1、获取当前页pc
		 * 2、通过bookService.findByCombination(book,pc),获取pageBean(未设置url)
		 * 3、获取url以被转发页面调用url
		 * 4、将pageBean保存到request中，并转发回list.jsp
		 */
		int pc=getPc(request);
		
		String press=request.getParameter("press");
		PageBean<Book> pb=bookService.findByPress(press,pc);
		
		String url=getUrl(request);
		pb.setUrl(url);
		
		request.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";
	}

	/**
	 * 加载图书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid=request.getParameter("bid");
		Book book=bookService.load(bid);
		request.setAttribute("book", book);
		return "f:/jsps/book/desc.jsp";
	}
	
	/**
	 * 通过录入时间查询，不过现在先通过出版时间查询
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByTime(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Book> bookList=bookService.findByTime();
		List<Author> authorList=authorService.findByIndex();
		request.setAttribute("bookList", bookList);
		request.setAttribute("authorList", authorList);
		return "f:/jsps/body.jsp";
	}
	
	public String findBySearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pc=getPc(request);
		
		String searchValue=request.getParameter("searchValue");
		PageBean<Book> pb=bookService.findBySearch(searchValue,pc);
		
		String url=getUrl(request);
		pb.setUrl(url);
		
		request.setAttribute("pb", pb);
		return "f:/jsps/book/list.jsp";
	}
}
