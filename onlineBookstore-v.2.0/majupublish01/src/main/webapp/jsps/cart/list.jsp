<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>cartItemList</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/cart/list.css'/>"></link>
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsps/js/cart/list.js'/>"></script>
  </head>
  
  <body>
    <div id="mainDiv">
    <c:choose>
    	<c:when test="${empty cartItemList}">
    	<table width="900px" align="center" style="padding:10px 10px 10px 20px; ">
    	<tr>
    		<td align="right" ><img align="top" src="<c:url value='/images/icon_empty.png'/>"/></td>
    		<td><span id="spanEmpty" >您的购物车中暂时没有商品</span></td>
    	</tr>
    	</table>
    	</c:when>
    	<c:otherwise>
    <table width="900" align="center" cellpadding="0" cellspacing="0">
    	<tr bgcolor="#efeae5" align="center">
    		<td align="left" width="50px"><input type="checkbox" id="selectAll" checked="checked" /><label>全选</label></td>
    		<td colspan="2">商品名称</td>
    		<td>单价</td>
    		<td>数量</td>
    		<td>小计</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach items="${cartItemList }" var="cartItem">
    	<tr align="center">
    		<td align="left"><input type="checkbox" name="checkboxBtn" value="${cartItem.cartItemId }" checked="checked"/></td>
    		<td align="left" width="70px"><a class="linkImage" href="<c:url value='/BookServlet?method=load&bid=${cartItem.book.bid }'/>"><img border="0" width="54" src="<c:url value='/${cartItem.book.image_w }'/>"/></a></td>
    		<td align="left" width="400px"><a href="<c:url value='/BookServlet?method=load&bid=${cartItem.book.bid }'/>">${cartItem.book.bname }</a></td>
    		<td>&yen;${cartItem.book.currPrice }</td>
    		<td><a class="jian" id="${cartItem.cartItemId }Jian" ></a><input class="quantity" id="${cartItem.cartItemId }Quantity" readonly="readonly" value="${cartItem.quantity }"/><a class="jia" id="${cartItem.cartItemId }Jia"></a></td>
    		<td class="price_x" width="100px"><span>&yen;</span><span id="${cartItem.cartItemId }Subtotal">${cartItem.getSubtotal() }</span></td>
    		<td><a href="<c:url value='/CartItemServlet?method=batchDelete&cartItemIds=${cartItem.cartItemId }'/>">删除</a></td>
    	</tr>
    	</c:forEach>
    	<tr>
    		<td class="batchDelete" colspan="4"><a href="javascript:_batchDelete()">批量删除</a></td>
    		<td colspan="3" align="right" class="Total">总计：<span class="price_zj" >&yen;<span id="total"></span></span></td>
    	</tr>
    	
    	<tr>
    		<td colspan="7" align="right"><a href="javascript:_loadCartItem()" id="jiesuan"></a></td>
    	</tr>
    </table>
    <form action="<c:url value='/CartItemServlet'/>" method="post" id="submitForm">
    	<input type="hidden" name="method" value="loadCartItem" />
    	<input type="hidden" name="cartItemIds" id="cartItemIds" />
    	<input type="hidden" name="total" id="totalSubmit" />
    </form>
    	</c:otherwise>
    </c:choose>
    </div>
  </body>
</html>
