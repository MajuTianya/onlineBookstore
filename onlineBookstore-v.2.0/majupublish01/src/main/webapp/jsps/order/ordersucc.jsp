<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ordersucc</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/order/ordersucc.css'/>" >
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>

  </head>
  
  <body>
  <div id="mainDiv">
  <div id="div1">
  	<span id="spanTitle">订单已生成</span>
  </div>
  <div id="div2">
  	<img src="<c:url value='/images/duihao.jpg'/>" id="img"/>
  	<dl>  
  		<dt>订单编号</dt>
  		<dd>${order.oid }</dd>
  		<dt>合计金额</dt>
  		<dd id="price_zj">&yen;${order.total }</dd>
  		<dt>收货地址</dt>
  		<dd>${order.address }</dd>
  	</dl>
  	<span>马驹’出版&nbsp;&nbsp;&nbsp;感谢您的支持，祝您购物愉快！</span>
  	<a href="<c:url value='/OrderServlet?method=paymentPre&oid=${order.oid }'/>" id="play" >支付</a>
  </div>
    </div>
  </body>
</html>
