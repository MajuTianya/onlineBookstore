package cn.maju.goods.order.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import cn.maju.goods.cart.domain.CartItem;
import cn.maju.goods.cart.service.CartItemService;
import cn.maju.goods.order.domain.Order;
import cn.maju.goods.order.domain.OrderItem;
import cn.maju.goods.order.service.OrderService;
import cn.maju.goods.page.PageBean;
import cn.maju.goods.user.domain.User;

public class OrderServlet extends BaseServlet {

	OrderService orderService=new OrderService();
	CartItemService cartItemService=new CartItemService();
	
	/**
	 * @Author cl
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String createOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.获取购物车条目的ids
		 * 2、通过cartItemService加载购物车条目cartItemList，以创建orderItemList
		 * 		一个cartItem对应一个orderItem
		 * 3、创建订单order
		 * 		设置oid
		 * 		设置下单时间为当前时间
		 * 		设置合计
		 * 		设置状态
		 * 		设置地址
		 * 		设置所属会员
		 * 		通过cartItemList来创建orderItemList,再把orderItemList设置给order
		 * 4、调用orderService的方法生成order
		 * 5、删除购物车中用来生成订单的条目
		 * 6、保存到Order到request中，转发到/jsps/order/ordersucc.jsp
		 */
		String cartItemIds=request.getParameter("cartItemIds");
		List<CartItem> cartItemList=cartItemService.loadCartItem(cartItemIds);
		
		Order order=new Order();
		order.setOid(CommonUtils.uuid());
		order.setOrdertime(String.format("%tF %<tT", new Date()));
		
		BigDecimal total=new BigDecimal("0");
		for(CartItem cartItem:cartItemList){
			total=total.add(new BigDecimal(Double.toString(cartItem.getSubtotal())));
		}
		order.setTotal(total.doubleValue());
		
		order.setStatus(1);//未付款状态
		String address=request.getParameter("address");
		order.setAddress(address);
		User owner=(User)request.getSession().getAttribute("userSession");
		order.setOwner(owner);
		
		List<OrderItem> orderItemList=new ArrayList<OrderItem>();
		for(CartItem cartItem:cartItemList){
			/*--设置orderItem--
			 * 设置订单条目orderItemId
			 * 设置一个条目所包含的书的数目quantity
			 * 设置条目小计subtotal
			 * 设置条目的内容book
			 * 设置条目所属的order
			 * 
			 * --把orderItem设置给order
			 */
			OrderItem orderItem=new OrderItem();
			orderItem.setOrderItemId(cartItem.getCartItemId());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setBook(cartItem.getBook());
			orderItem.setOrder(order);
			
			orderItemList.add(orderItem);
		}
		order.setOrderItemList(orderItemList);
		orderService.createOrder(order);
		cartItemService.batchDelete(cartItemIds);
		
		request.setAttribute("order", order);
		return "f:/jsps/order/ordersucc.jsp";
	}
	
	public String myOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1、得到pc
		 * 2、得到url
		 * 3、pc和uid调用orderService.myOrders(uid,pc)返回pageBean
		 * 4、设置pc、url为pageBean的成员
		 * 5、保存pageBean并返回到/jsps/order/list.jsp页面
		 */
		int pc=getPc(request);
		String url=getUrl(request);
		User owner=(User)request.getSession().getAttribute("userSession");
		PageBean<Order> pb=orderService.myOrders(owner.getUid(),pc);
		
		pb.setUrl(url);
		
		request.setAttribute("pb", pb);
		return "f:/jsps/order/list.jsp";
	}

	/*
	 * 获得url
	 */
	private String getUrl(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String url=request.getRequestURI()+"?"+request.getQueryString();
		int index=url.indexOf("&pc");
		if(index==-1){
			return url;
		}else{
			int index2=url.indexOf("&", index+1);
			if(index2==-1){
				return url.substring(0,index);
			}else{
				return url.substring(0,index)+url.substring(index+1);
			}
		}
	}

	/*
	 * 获得pc
	 */
	private int getPc(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String pc=request.getParameter("pc");
		if(pc==null){
			return 1;
		}else{
			try{
				return Integer.parseInt(pc);
			}catch(RuntimeException e){}
		}
		return 1;
	}
	
	/**
	 * 查看订单时健在订单详细内容
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid=request.getParameter("oid");
		Order order=orderService.load(oid);
		String btn=request.getParameter("btn");
		request.setAttribute("btn", btn);
		request.setAttribute("order", order);
		return "f:/jsps/order/desc.jsp";
	}
	
	/**
	 * 取消订单状态
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String cancel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid=request.getParameter("oid");
		int status=orderService.findStatusByOid(oid);
		if(status!=1){
			request.setAttribute("code", "error");
			request.setAttribute("msg","状态不对，不能取消订单！");
			return "f:/jsps/msg.jsp";
		}
		orderService.updateStatus(oid,5);
		request.setAttribute("code", "success");
		request.setAttribute("msg", "您的订单已取消，您不后悔吗？");
		return "f:/jsps/msg.jsp";
	}
	
	/**
	 * 确认收货
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid=request.getParameter("oid");
		int status=orderService.findStatusByOid(oid);
		if(status!=3){
			request.setAttribute("code", "error");
			request.setAttribute("msg","状态不对，不能确认收货！");
			return "f:/jsps/msg.jsp";
		}
		orderService.updateStatus(oid,4);
		request.setAttribute("code", "success");
		request.setAttribute("msg", "恭喜您，交易成功！");
		return "f:/jsps/msg.jsp";
	}
	
	/**
	 * 支付准备
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String paymentPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid=request.getParameter("oid");
		Order order=orderService.load(oid);
		request.setAttribute("order", order);
		return "f:/jsps/order/pay.jsp";
	}
	
	/**
	 * 支付方法
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String payment(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("payment.properties"));
		/*
		 * 1. 准备13个参数
		 */
		String p0_Cmd = "Buy";//业务类型，固定值Buy
		String p1_MerId = props.getProperty("p1_MerId");//商号编码，在易宝的唯一标识
		String p2_Order = req.getParameter("oid");//订单编码
		String p3_Amt = "0.01";//支付金额
		String p4_Cur = "CNY";//交易币种，固定值CNY
		String p5_Pid = "";//商品名称
		String p6_Pcat = "";//商品种类
		String p7_Pdesc = "";//商品描述
		String p8_Url = props.getProperty("p8_Url");//在支付成功后，易宝会访问这个地址。
		String p9_SAF = "";//送货地址
		String pa_MP = "";//扩展信息
		String pd_FrpId = req.getParameter("yh");//支付通道
		String pr_NeedResponse = "1";//应答机制，固定值1
		
		/*
		 * 2. 计算hmac
		 * 需要13个参数
		 * 需要keyValue
		 * 需要加密算法
		 */
		String keyValue = props.getProperty("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);
		
		/*
		 * 3. 重定向到易宝的支付网关
		 */
		StringBuilder sb = new StringBuilder("https://www.yeepay.com/app-merchant-proxy/node");
		sb.append("?").append("p0_Cmd=").append(p0_Cmd);
		sb.append("&").append("p1_MerId=").append(p1_MerId);
		sb.append("&").append("p2_Order=").append(p2_Order);
		sb.append("&").append("p3_Amt=").append(p3_Amt);
		sb.append("&").append("p4_Cur=").append(p4_Cur);
		sb.append("&").append("p5_Pid=").append(p5_Pid);
		sb.append("&").append("p6_Pcat=").append(p6_Pcat);
		sb.append("&").append("p7_Pdesc=").append(p7_Pdesc);
		sb.append("&").append("p8_Url=").append(p8_Url);
		sb.append("&").append("p9_SAF=").append(p9_SAF);
		sb.append("&").append("pa_MP=").append(pa_MP);
		sb.append("&").append("pd_FrpId=").append(pd_FrpId);
		sb.append("&").append("pr_NeedResponse=").append(pr_NeedResponse);
		sb.append("&").append("hmac=").append(hmac);
		
		resp.sendRedirect(sb.toString());
		return null;
	}
	
	/**
	 * 回馈方法
	 * 当支付成功时，易宝会访问这里
	 * 用两种方法访问：
	 * 1. 引导用户的浏览器重定向(如果用户关闭了浏览器，就不能访问这里了)
	 * 2. 易宝的服务器会使用点对点通讯的方法访问这个方法。（必须回馈success，不然易宝服务器会一直调用这个方法）
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String back(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 获取12个参数
		 */
		String p1_MerId = req.getParameter("p1_MerId");
		String r0_Cmd = req.getParameter("r0_Cmd");
		String r1_Code = req.getParameter("r1_Code");
		String r2_TrxId = req.getParameter("r2_TrxId");
		String r3_Amt = req.getParameter("r3_Amt");
		String r4_Cur = req.getParameter("r4_Cur");
		String r5_Pid = req.getParameter("r5_Pid");
		String r6_Order = req.getParameter("r6_Order");
		String r7_Uid = req.getParameter("r7_Uid");
		String r8_MP = req.getParameter("r8_MP");
		String r9_BType = req.getParameter("r9_BType");
		String hmac = req.getParameter("hmac");
		/*
		 * 2. 获取keyValue
		 */
		Properties props = new Properties();
		props.load(this.getClass().getClassLoader().getResourceAsStream("payment.properties"));
		String keyValue = props.getProperty("keyValue");
		/*
		 * 3. 调用PaymentUtil的校验方法来校验调用者的身份
		 *   >如果校验失败：保存错误信息，转发到msg.jsp
		 *   >如果校验通过：
		 *     * 判断访问的方法是重定向还是点对点，如果要是重定向
		 *     修改订单状态，保存成功信息，转发到msg.jsp
		 *     * 如果是点对点：修改订单状态，返回success
		 */
		boolean bool = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId,
				r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType,
				keyValue);
		if(!bool) {
			req.setAttribute("code", "error");
			req.setAttribute("msg", "无效的签名，支付失败！");
			return "f:/jsps/msg.jsp";
		}
		if(r1_Code.equals("1")) {
			orderService.updateStatus(r6_Order, 2);
			if(r9_BType.equals("1")) {
				req.setAttribute("code", "success");
				req.setAttribute("msg", "恭喜，支付成功！");
				return "f:/jsps/msg.jsp";				
			} else if(r9_BType.equals("2")) {
				resp.getWriter().print("success");
			}
		}
		return null;
	}
}
