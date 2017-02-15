<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>list</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/book/list.css'/>" ></link>
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/pager/pager.css'/>" ></link>
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsps/js/book/list.js'/>"></script>

  </head>
  
  <body>
    <div class="mainDiv">
    <p class="index_hr"></p>
    <span style="font:14px/20px '微软雅黑'; color:#646464; height:20px; ">一级分类--->二级分类</span>
    <div class="listDiv">
    <ul>
    	<c:forEach items="${pb.dataList }" var="book" >
    		<li>
    		<div class="inner">
    			<a class="picture" href="<c:url value='/BookServlet?method=load&bid=${book.bid }'/>" target="bodyIframe"><img src="<c:url value='/${book.image_b }'/>" /></a>
    			<p class="price" >
    			<span class="price_x">&yen;${book.currPrice }</span>
    			<span class="price_y">&yen;${book.price }</span>
    			(<span class="price_z">${book.discount }</span>)
    			</p>
    			<p><a class="bookname" href="<c:url value='/BookServlet?method=load&bid=${book.bid }'/>" target="bodyIframe" title="${book.bname }">${book.bname}</a></p>
    			<p><a class="bookauthor"  href="<c:url value='/BookServlet?method=findByAuthor&author=${book.author }'/>"  target="bodyIframe" title="${book.author }" >${book.author}</a></p>
    			<p><span>出版社：</span><a class="bookauthor" href="<c:url value='/BookServlet?method=findByPress&press=${book.press }'/>"  target="bodyIframe" title="${book.press }">${book.press}</a></p>
    			<p><span >出版时间：</span>${book.publishtime }</p>
    		</div>
    	</li>
    	</c:forEach>
    </ul>
    </div>
    <div>
    	<%@ include file="/jsps/pager/pager.jsp" %>
    </div>
    </div>
  </body>
</html>
