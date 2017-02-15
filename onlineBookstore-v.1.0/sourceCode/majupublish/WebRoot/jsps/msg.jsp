<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>msg</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	body {
		font-size: 10pt;
		color: #404040;
		width: 890px;
		height: 350px;
	}
	
#mainDiv{margin:70px 0px 0px 0px; display:inline-block; }
	.divBody {
		margin-left: 20px;
	}
	
	
a {text-decoration: none;}
a:visited {color: #018BD3;}
a:hover {color:#FF6600; text-decoration: underline; }
	</style>

  </head>
  
  <body>
  <div id="mainDiv">
   <c:choose>
  	<c:when test="${code eq 'success' }">
  		<c:set var="img" value="/images/success.gif"/>
  	</c:when>
  	<c:when test="${code eq 'error' }">
  		<c:set var="img" value="/images/sorry2.gif"/>
  	</c:when>
  </c:choose>
<div class="divBody">
	<div>
	  <div style="margin: 50px;">
		<img style="margin-right: 50px;" src="<c:url value='${img }'/>" width="150" height="150"/>
		<span style="margin-right: 50px; font-size: 30px; color: #99ccff; font-weight: 900;">${msg }</span>
	  	<span style="margin-right: 50px;"><a target="_top" href="<c:url value='/jsps/user/login.jsp'/>">登录</a></span>
		<span style="margin-right: 50px;"><a target="_top" href="<c:url value='/index.jsp'/>">首页</a></span>
	  </div>
	</div>
</div>
  </div>
  </body>
</html>
