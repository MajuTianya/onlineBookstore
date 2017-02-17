<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>desc</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/book/desc.css'/>"></link>
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>" ></script>
	<script type="text/javascript" src="<c:url value='/jsps/js/book/desc.js'/>" ></script>
  </head>
  
  <body>
   <div id="mainDiv">
   <p class="index_hr"></p>
   <div id="divBookName">${book.bname }</div>
    <div>
    	<img align="top" width="350" height="350" src="<c:url value='/${book.image_w }'/>"/>
    	<div id="divBookDesc">
    		<ul>
    			<li>商品编号：${book.bid }</li>
    			<li>当前价：<span id="price_x">&yen;${book.currPrice }</span></li>
    			<li>定价：<span id="price_y">&yen;${book.price }</span>折扣：<span id="price_z">${book.discount }</span>折</li>
    		</ul>
    		<hr style="margin-left:50px; color:#dcdcdc; height:1px; "/>
    		<table>
    			<tr><td colspan="3">作者：${book.author } 著</td></tr>
    			<tr><td colspan="3">出版社：${book.press }</td></tr>
    			<tr><td colspan="3">出版时间：${book.publishtime }</td></tr>
    			<tr><td width="180px">版次：${book.edition }</td><td>页数：${book.pageNum }</td><td>字数：${book.wordNum }</td></tr>
    			<tr><td>应刷时间：${book.printtime }</td><td>开本：${book.booksize }</td><td>纸张：${book.paper }</td></tr>
    		</table>
    		<div id="divForm">
    			<form action="<c:url value='/CartItemServlet'/>" id="form1">
    				<input type="hidden" name="method" value="add"/>
    				<input type="hidden" name="bid" value="${book.bid }" />
    			我要买：<a class="jian" id="jian" ></a><input type="text" id="quantity" name="quantity" style="width:40px; text-align:center;"  value="1" readonly="readonly" /><a class="jia" id="jia"></a>件
    			</form>
    			<a id="submitBtn" href="javascript:$('#form1').submit()" target="bodyIframe"></a>
    		</div>
    	</div>
    </div>
   </div>
  </body>
</html>
