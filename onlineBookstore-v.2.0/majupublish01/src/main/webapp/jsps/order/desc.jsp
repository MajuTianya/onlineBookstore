<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>OrderDesc</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/order/desc.css'/>" ></link>
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>

  </head>
  
  <body>
  	<div id="mainDiv">
  	<div id="orderDiv">
    	<span>订单号：${order.oid }
    		<c:if test="${order.status eq 1 }">(等待付款)</c:if>
    		<c:if test="${order.status eq 2 }">(准备发货)</c:if>
    		<c:if test="${order.status eq 3 }">(等待确认)</c:if>
    		<c:if test="${order.status eq 4 }">(交易成功)</c:if>
    		<c:if test="${order.status eq 5 }">(已取消)</c:if>
    	</span>
    	<span>下单时间：${order.ordertime }</span>
    </div>
    <div id="contentDiv">
    	<div id="div2">
    	<dl>
    		<dt>收货人信息</dt>
    		<dd>${order.address }</dd>
    	</dl>
    	</div>
    	<div id="div2">
    	<dl>
    		<dt>商品清单</dt>
    		<dd>
    			<table cellspacing="0px" cellpadding="0px">
    				<tr>
    					<th class="th">商品名称</th>
    					<th class="th" align="left">单价</th>
    					<th class="th" align="left">数量</th>
    					<th class="th" align="left">小计</th>
    				</tr>
    				
    				<c:forEach items="${order.orderItemList }" var="orderItem">
    				<tr style="padding-top:20px; padding-bottom:20px; ">
    					<td class="td"width="400px">
    						<div class="bookname">
    							<img align="middle" width="70px"src="<c:url value='/${orderItem.book.image_b }'/>">
    							<a href="">${orderItem.book.bname }</a>
    						</div>
    					</td>
    					<td class="td">&yen;${orderItem.book.currPrice }</td>
    					<td class="td">${orderItem.quantity }</td>
    					<td class="td">&yen;${orderItem.subtotal }</td>
    				</tr>
    				</c:forEach>
    				
    			</table>
    		</dd>
    	</dl>
    	</div>
    	<div style="margin:10px 10px 10px 600px; ">
    		<span style="font-weight:900; font-size:15px; ">合计金额：</span><span class="price_t">&yen;${order.total }</span><br/>
    		<c:if test="${order.status eq 1 }">
    			<a href="<c:url value='/OrderServlet?method=paymentPre&oid=${order.oid }'/>" class="pay"></a><br/>
    		</c:if>
    		<c:if test="${order.status eq 1 and btn eq 'cancel' }">
    			<a href="<c:url value='/OrderServlet?method=cancel&oid=${order.oid }'/>" id="cancel">取消订单</a><br/>
    		</c:if>
    		<c:if test="${order.status eq 3 and btn eq 'confirm' }">
    			<a href="<c:url value='/OrderServlet?method=confirm&oid=${order.oid }'/>" id="confirm" >确认收货</a>
    		</c:if>
    	</div>
    </div>
  	</div>
  </body>
</html>
