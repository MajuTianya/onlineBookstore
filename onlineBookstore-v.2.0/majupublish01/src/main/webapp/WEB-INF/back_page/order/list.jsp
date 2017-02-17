<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>mujupublish-list</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/order/list.css'/>" />
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 订单管理 - 列表 
	<c:choose>
	<c:when test="${status eq 1 }">- 未付款</c:when>
	<c:when test="${status eq 2 }">- 已付款</c:when>
	<c:when test="${status eq 3 }">- 已发货</c:when>
	<c:when test="${status eq 4 }">- 交易成功</c:when>
	<c:when test="${status eq 5 }">- 已取消</c:when>
	</c:choose>
	</div>
	<div class="clear"></div>
</div>
<div class="body-box">
<form method="post" id="tableForm">
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th colspan="2">商品信息</th>
			<th>金额</th>
			<th>订单状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		
		<c:forEach items="${pb.dataList }" var="order">	
	
		<tr class="tt">
			<td width="320px">订单号：<a  href="<c:url value='/admin/order/getOrderById.do?oid=${order.oid }'/>">${order.oid }</a></td>
			<td width="200px">下单时间：${order.ordertime }</td>
			<td width="178px">&nbsp;</td>
			<td width="205px">&nbsp;</td>
			<td>&nbsp;</td>
		</tr>	

		<tr style="padding-top: 10px; padding-bottom: 10px;">
			<td colspan="2">


  <c:forEach items="${order.orderItemList }" var="orderItem">
	    <img border="0" width="70" src="${orderItem.image_b }"/>
  </c:forEach>

			</td>
			<td style="padding-left: 0" align="center">
				<span class="price_t">&yen;${order.total }</span>
			</td>
			<td align="center">
<c:choose>
	<c:when test="${order.status eq 1 }">(等待付款)</c:when>
	<c:when test="${order.status eq 2 }">(准备发货)</c:when>
	<c:when test="${order.status eq 3 }">(等待确认)</c:when>
	<c:when test="${order.status eq 4 }">(交易成功)</c:when>
	<c:when test="${order.status eq 5 }">(已取消)</c:when>
</c:choose>	
			</td>
						
			<td align="center">
			<a href="<c:url value='/admin/order/getOrderById.do?oid=${order.oid }'/>">查看</a>
<c:if test="${order.status eq 1 }">
				|<a href="<c:url value='/admin/order/getOrderById.do?oid=${order.oid }&btn=cancel'/>">取消</a>					
</c:if>
<c:if test="${order.status eq 2 }">
				|<a href="<c:url value='/admin/order/getOrderById.do?oid=${order.oid }&btn=deliver'/>">发货</a>
</c:if>			

			</td>
		</tr>
</c:forEach>

	</tbody>
</table>
<div style="margin-top:15px;">
<!-- 	<input class="del-button" type="button" value="取消" onclick="optCancel();"/>
	<input class="submit" type="button" value="通过" onclick="optPass();"/> -->
</div>
<%@ include file="/WEB-INF/back_page/pager/pager.jsp" %>
</form>
</div>
</body>
</html>