<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/order/list.css'/>" >
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>

  </head>
  
  <body>
     <div id="divMain2">
  	<div id="divTitle">
  		<span style="margin-left:150px; ">商品信息</span>
  		<span style="margin-left:330px; ">金额</span>
  		<span style="margin-left:105px; ">订单状态</span>
  		<span style="margin-left:80px; ">操作</span>
  	</div>
  	<br>
  	
  	<table align="center" border="0px" cellpadding="0px" cellspacing="0px" width="940px" >
  	<c:forEach items="${pb.dataList }" var="order">
  	<tr class="orderTitle" >
  			<td width="320px">订单号<a href="<c:url value='/OrderServlet?method=load&oid=${order.oid }'/>">${order.oid }</a></td>
  			<td width="200px">下单时间:${order.ordertime }</td>
  			<td>&nbsp;</td>
  			<td>&nbsp;</td>
  			<td>&nbsp;</td>
  		</tr>
  		<tr style="padding-top:10px; padding-bottom:10px; ">
  			<td colspan="2">
  			<c:forEach items="${order.orderItemList }" var="orderItem">
  				<a class="img" href="<c:url value='/BookServlet?method=load&bid=${orderItem.book.bid }'/>"><img width="70px" src="<c:url value='/${orderItem.book.image_b }'/>"/></a>
  			</c:forEach>
  			</td>
  			<td class="price_s" width="115px">&yen;${order.total }</td>
  			<td width="142px">
  				<c:if test="${order.status eq 1 }">（等待付款）</c:if>
  				<c:if test="${order.status eq 2 }">（准备发货）</c:if>
  				<c:if test="${order.status eq 3 }">（等待确认）</c:if>
  				<c:if test="${order.status eq 4 }">（交易成功）</c:if>
  				<c:if test="${order.status eq 5 }">（已取消）</c:if>
  			</td>
  			<td>
  			<a href="<c:url value='/OrderServlet?method=load&oid=${order.oid }'/>">查看</a><br>
  			<c:if test="${order.status eq 1 }">
  			<a href="<c:url value='/OrderServlet?method=load&oid=${order.oid }'/>">支付</a><br>
  			<a href="<c:url value='/OrderServlet?method=load&oid=${order.oid }&btn=cancel'/>">取消</a>
  			</c:if>
  			<c:if test="${order.status eq 3 }">
  				<a href="<c:url value='/OrderServlet?method=load&oid=${order.oid }&btn=confirm'/>">确认收货</a><br>
  			</c:if>
  			</td>
  		</tr>
  	</c:forEach>
  	</table>
  	<div>
  	<%@include file="/jsps/pager/pager.jsp" %>
  	</div>
  </div>
  </body>
</html>
