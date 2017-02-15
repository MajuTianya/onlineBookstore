<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>bottom</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/bottom.css'/>">
  </head>
  
  <body>
  	<div id="mainDiv0">
  	<p class="index_hr"></p>
    <p align="center" id="friends">
    <span><a href="<c:url value='http://www.tsu.edu.cn/'/>" target="_blank">泰山学院</a></span>&nbsp;&nbsp;|&nbsp;&nbsp;
    <span><a href="<c:url value='http://www.jjwxc.net/'/>" target="_blank">晋江文学城</a></span>&nbsp;&nbsp;|&nbsp;&nbsp;
    <span><a href="<c:url value='http://www.csdn.net/'/>" target="_blank">CSDN</a></span>&nbsp;&nbsp;|&nbsp;&nbsp;
    <span><a href="<c:url value='http://www.sina.com.cn/'/>" target="_blank">新浪（SINA）</a></span>&nbsp;&nbsp;|&nbsp;&nbsp;
    <span><a href="<c:url value='http://www.qq.com/'/>" target="_blank">腾讯(Tencent)</a></span>&nbsp;&nbsp;
    </p>
    <p align="center" id="validate">
    	Copyright&nbsp;&nbsp;©&nbsp;&nbsp;<a href="<c:url value='/index.jsp'/>" target="_blank">马驹’出版</a>&nbsp;&nbsp;2015-2025,&nbsp;&nbsp;All rights reserved.<span><img src="<c:url value='/images/validate.gif'/>"/></span>京ICP证041189号出版物经营许可证 新出发京批字第直0673号
    </p>
  	</div>
  </body>
</html>
