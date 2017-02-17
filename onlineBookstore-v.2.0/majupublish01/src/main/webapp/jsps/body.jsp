<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>body</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/body.css'/>"></link>
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsps/js/body.js'/>"></script>
  </head>
  
  <body>
  	<div id="mainDiv">
  		<div id="bannerDiv">
  			<div><a href="<c:url value='/BookServlet?method=load&bid=12345678901234567890123456789001'/>" target="bodyIframe"><img src="<c:url value='/images/banner1.png'/>" title="不解枫情" /></a></div>
  			<div class="smark"><a href="<c:url value='/BookServlet?method=load&bid=12345678901234567890123456789002'/>" target="bodyIframe"><img src="<c:url value='/images/banner2.png'/>" title="何以笙箫默" /></a></div>
  			<div class="smark"><a href="<c:url value='/BookServlet?method=load&bid=12345678901234567890123456789003'/>" target="bodyIframe"><img src="<c:url value='/images/banner3.png'/>" title="儿童的文学世界·我的文学课（4年级）" /></a></div>
  			<div class="smark"><a href="<c:url value='/BookServlet?method=load&bid=12345678901234567890123456789004'/>" target="bodyIframe"><img src="<c:url value='/images/banner4.png'/>" title="2014中国年度小学生作文" /></a></div>
  		</div>
  		<ul id="bannerNav">
  				<li class="current"></li>
  				<li></li>
  				<li></li>
  				<li></li>
  		</ul>
  		<div class="contentDiv">
  		
  		<div class="contentBook">
  		<p class="index_hr"></p>
  		<div class="content">
  			<h1 class="contentTitle">新书上架</h1>
  			<ul>
  			<c:forEach items="${bookList }" var="book">
  				<li>
  					<dl>
  						<dt><a href="<c:url value='/BookServlet?method=load&bid=${book.bid }'/>" target="bodyIframe"><img src="<c:url value='/${book.image_b }'/>"/></a></dt>
  						<dd>
  							<a href="<c:url value='/BookServlet?method=load&bid=${book.bid }'/>" target="bodyIframe" class="bookname">${book.bname }</a><br/>
  							<a href="" class="bookauthor">${book.author }·著</a><br/>
  							<a href="<c:url value='/BookServlet?method=load&bid=${book.bid }'/>" target="bodyIframe"><img src="<c:url value='/images/bt_buy.jpg'/>"/></a>
  						</dd>
  					</dl>
  				</li>
  			</c:forEach>
  			</ul>
  		</div>
  		</div>
  		
  		<div class="contentAuthor">
  		<p class="index_hr"></p>
  		<div class="contentAuthorList">
  		<h1 class="contentAuthorTitle"><span>下一个就是你</span><span class="moreAuthor"><img src="<c:url value='/images/icon_tel.jpg'/>"/><a href="#">更多作者 →</a></span></h1>
  			<ul>
  			<c:forEach items="${authorList }" var="author">
  			<li>
  				<dl>
  					<dt><img src="<c:url value='/${author.image_s }'/>"/></dt>
  					<dd>
  						<span class="authorName">${author.aname }</span><br/>
  						<span class="works">《<a>${author.work1 }</a>》、《<a>${author.work2 }</a>》、《<a>${author.work3 }</a>》</span><br/>
  						<span class="authorInfo">${author.describle }</span><br/>
  					</dd>
  				</dl>
  			</li>
  			</c:forEach>
  			</ul>
  		</div>
  		<p>1</p>
  	</div>
  	</div>
  	</div>
  </body>
</html>
