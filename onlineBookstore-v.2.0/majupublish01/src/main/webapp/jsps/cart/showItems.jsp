<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>showItems</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/cart/showItems.css'/>" >
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript">
  $(function(){
	 //计算总计
	 var total=0;
	 $(".subtotal").each(function(){
		 var subtotal=$(this).text();
		 total+=Number(subtotal);
	 });
	 $("#total").text(round(total,2));
  });
  
  //精确到2位小数
  function round(numb,dec){
	  //1、把numb转换成字符串
	  //2、获取小数点位置：a，没有小数点，返回numb；b，有小数点，获取小数位数n:a,n<dec,返回numb;否则，第三步
	  //3、numb*dec*10得b，对b进行四舍五入得numb，返回numb/(dec*10).
	  var nums=numb+"";
	  index=nums.indexOf(".");
	  if(index==-1){
		  return numb;
	  }else{
		  var n=nums.length-index-1;
		  if(n<dec){
			  return numb;
		  }else{
			  var a=Math.pow(10, dec);
			  numb=numb*a;
			  numb=Math.round(numb);
			  return numb/a;
		  }
	  }
  }
  </script>

  </head>
  
  <body>
    <div id="mainDiv">
    <form id="form1" action="<c:url value='/OrderServlet'/>" method="post">
  	<input type="hidden" name="method" value="createOrder"/>
  	<input type="hidden" name="cartItemIds" value="${cartItemIds }"/>
    <table width="900px" cellpadding="0" cellspacing="0" align="center">
    	<tr align="left"><td colspan="5" class="title">生成订单</td></tr>
    	<tr align="center"><td colspan="2">图书名称</td><td>单价</td><td>数量</td><td>小计</td></tr>
    	
    	<c:forEach items="${cartItemList }" var="cartItem">
    		<tr align="center">
    		<td width="70px"><a class="images" href="<c:url value='/BookServlet?method=load&bid=${cartItem.book.bid }'/>" ><img border="0" width="54px" src="<c:url value='/${cartItem.book.image_b }'/>"/></a></td>
    		<td align="left" width="400px"><a href="<c:url value='/BookServlet?method=load&bid=${cartItem.book.bid }'/>">${cartItem.book.bname }</a></td>
    		<td>&yen;${cartItem.book.currPrice }</td> 
    		<td>${cartItem.quantity }</td>
    		<td><span class="price_x">&yen;<span class="subtotal">${cartItem.getSubtotal() }</span></span>
    	</tr>
    	</c:forEach>
    	
    	<tr align="right"><td colspan="5">总计：<span class="price_zj">&yen;<span id="total"></span></span></td></tr>
    	<tr align="left"><td colspan="5" class="title">收货地址</td></tr>
    	<tr align="left"><td colspan="5"><input type="text" id="address" name="address"/></td></tr>
    	<tr align="right"><td colspan="5" style="border-top-width:4px"><a id="submitBtn" href="javascript:$('#form1').submit();">提交订单</a></td></tr>
    </table>
    </form>
    </div>
  </body>
</html>
