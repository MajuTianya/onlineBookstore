<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>马驹'出版首页</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta> 
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/topTitle.css'/>"></link>
  </head>
  
  <body>
  	<div id="topTitle">
  		<div class="mainDiv">
  		<c:choose>
  		<c:when test="${empty sessionScope.userSession }">
  		<ul class="leftUl">
  			<!-- 未登录是显示 -->
  			<li><a href="<c:url value='/jsps/user/login.jsp'/>" target="_blank">登录</a>&nbsp;|&nbsp;</li>
  			<li><a href="<c:url value='/jsps/user/regist.jsp'/>" target="_blank">注册</a>&nbsp;</li>
  		</ul>
  		</c:when>
  		<c:otherwise>
  		<ul class="leftUl">
  			<!-- 登陆后显示 -->
  			<!-- 左边 -->
  			<li>您好,&nbsp;${sessionScope.userSession.loginname }&nbsp;&nbsp;&nbsp;&nbsp;</li>
  			<li><a href="<c:url value='/jsps/user/pwd.jsp'/>" target="bodyIframe">修改密码</a>&nbsp;|&nbsp;</li>
  			<li><a href="<c:url value='/UserServlet?method=quit'/>" target="_top">退出</a>&nbsp;</li>
  			<!-- 右边 -->
  		</ul>
  		<ul class="rightUl">
  			<li><a href="<c:url value='/CartItemServlet?method=myCart'/>" target="bodyIframe">我的购物车</a>&nbsp;|&nbsp;</li>
  			<li><a href="<c:url value='/OrderServlet?method=myOrders'/>" target="bodyIframe">我的订单</a>&nbsp;</li>
  		</ul>
  		</c:otherwise>
  		</c:choose>
  		</div>
  	</div>
  </body>
</html>
