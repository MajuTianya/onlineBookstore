<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>topBanner</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/topBanner.css'/>"></link>
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsps/js/topBanner.js'/>"></script>
  </head>
  
  <body>
  	<div class="mainDiv">
  	<div style="float:left; margin-top:40px; "><a href="<c:url value='/index.jsp'/>" target="_top"><img src="<c:url value='/images/logo.png'/>" alt="马驹出版的logo"/></a></div>
  	<div style="float:left;margin-top:10px; ">
  		<ul>
  			<li><a class="set" href="<c:url value='/BookServlet?method=findByTime'/>" target="bodyIframe">首页</a></li>
  			<li><a>DIY</a></li>
  			<li><a>供求BBS</a></li>
  			<li><a href="http://weibo.com/714713450?topnav=1&wvr=6&topsug=1#_loginLayer_1431398761083" target="_blank">联系我们</a></li>
  		</ul>
  	</div>
  	
  	<form action="<c:url value='/BookServlet'/>" id="form1" method="get" target="bodyIframe">
  	<div style="float:right; margin-top:60px; height:25px; text-align:center; inline-height:25px; ">
  	<input type="hidden" name="method" value="findBySearch"/>
  	<input type="text" id="searchValue" name="searchValue"/><a id="search" href="javascript:document.getElementById('form1').submit()"><img width="27px" height="27px" src="<c:url value='/images/search.png'/>"/></a></div>	
  	</form>
  	</div>
  </body>
</html>
