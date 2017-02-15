package cn.maju.goods.cart.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.maju.goods.book.domain.Book;
import cn.maju.goods.cart.domain.CartItem;
import cn.maju.goods.cart.service.CartItemService;
import cn.maju.goods.user.domain.User;

public class CartItemServlet extends BaseServlet {
	CartItemService cartItemService=new CartItemService();

	/**
	 * 更新购物车条目数量
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateQuantity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cartItemId=request.getParameter("cartItem");
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		CartItem cartItem=cartItemService.updateQuantity(cartItemId,quantity);
		StringBuilder result=new StringBuilder("{");
		result.append("\"quantity\":"+cartItem.getQuantity());
		result.append(",");
		result.append("\"subtotal\":"+cartItem.getSubtotal());
		result.append("}");
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * 按用户查询查看购物车
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String myCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1、得到uid
		 * 2、通过uid查询cartItemList
		 * 3、保存到request中，并返回给cart/list.jsp页面
		 */
		User user=(User) request.getSession().getAttribute("userSession");
		String uid=user.getUid();
		List<CartItem> cartItemList=cartItemService.myCart(uid);
		request.setAttribute("cartItemList", cartItemList);
		return "f:/jsps/cart/list.jsp";
	}
	
	/**
	 * 增加购物车条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 把表单数据存储为map数据集
		 * 把map封装cartItem
		 * 把map封装成book
		 * 从session得到owner对象
		 * 把book、owner设置为cartItem的数据成员
		 * 显示购物车
		 */
		Map<String, String[]> map=request.getParameterMap();
		CartItem cartItem=CommonUtils.toBean(map, CartItem.class);
		Book book=CommonUtils.toBean(map, Book.class);
		User owner=(User) request.getSession().getAttribute("userSession");
		CartItem ctId=cartItemService.findByBidAndUid(book.getBid(),owner.getUid());
		if(ctId!=null){
			cartItemService.updateQuantity(ctId.getCartItemId(),cartItem.getQuantity()+ctId.getQuantity());
		}else{
			cartItem.setCartItemId(CommonUtils.uuid());
			cartItem.setBook(book);
			cartItem.setOwner(owner);
			cartItemService.add(cartItem);
		}
		return myCart(request,response);
	}
	
	/**
	 * 批量删除
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String batchDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cartItemIds=request.getParameter("cartItemIds");
		cartItemService.batchDelete(cartItemIds);
		return myCart(request,response);
	}

	/**
	 * 批量加载--计算按钮触发
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadCartItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cartItemIds=request.getParameter("cartItemIds");
		List<CartItem> cartItemList=cartItemService.loadCartItem(cartItemIds);
		String total=request.getParameter("total");
		request.setAttribute("total", total);
		request.setAttribute("cartItemList",cartItemList);
		request.setAttribute("cartItemIds", cartItemIds);
		return "/jsps/cart/showItems.jsp";
	}
}
