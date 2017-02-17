<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>majupublish-desc</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/res/css/order/desc.css'/>" />
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 订单管理 - 
		<c:choose>
		<c:when test="${order.status eq 1 and btn eq 'cancel' }">取消</c:when>
		<c:when test="${order.status eq 2 and btn eq 'deliver' }">发货</c:when>
		<c:when test="${btn ne 'cancel' and btn ne 'deliver' }">查看</c:when>
		</c:choose>
		</div>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form id="jvForm" action="v_list.do" method="post">
			<table cellspacing="1" cellpadding="2" border="0" width="100%" class="pn-ftable">
				<tbody>
					<tr>
					<th colspan="4" style="height: 27px; background-color: #e6f2fe; text-align:left" >订单详细</th>
					</tr>
					<tr>
						<td width="12%" class="pn-flabel pn-flabel-h">收货人:</td>
						<td width="38%" colspan="1" class="pn-fcontent">陈丽</td>
						<td width="12%" class="pn-flabel pn-flabel-h">收货地址:</td>
						<td width="38%" colspan="1" class="pn-fcontent">${order.address }</td>
					</tr>
					<tr>
						<td width="12%" class="pn-flabel pn-flabel-h">订单号:</td>
						<td width="38%" colspan="1" class="pn-fcontent">${order.oid }</td>
						<td width="12%" class="pn-flabel pn-flabel-h">订单状态:</td>
						<td width="38%" colspan="1" class="pn-fcontent">
						<c:choose>
							<c:when test="${order.status eq 1 }">(等待付款)</c:when>
							<c:when test="${order.status eq 2 }">(准备发货)</c:when>
							<c:when test="${order.status eq 3 }">(等待确认)</c:when>
							<c:when test="${order.status eq 4 }">(交易成功)</c:when>
							<c:when test="${order.status eq 5 }">(已取消)</c:when>
						</c:choose>
						</td>
					</tr>
					<tr>
						<td width="12%" class="pn-flabel pn-flabel-h">创建时间:</td>
						<td width="38%" colspan="1" class="pn-fcontent">${order.ordertime }</td>
						<td width="12%" class="pn-flabel pn-flabel-h">合计:</td>
						<td width="38%" colspan="1" class="pn-fcontent"><span class="price_t">&yen;${order.total }</span></td>
					</tr>
				</tbody>
			</table>
			<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
				<tbody class="pn-ltbody">
					<tr>
					<th colspan="5" style="height: 27px; background-color: #e6f2fe; text-align:left">商品清单</th>
					</tr>
					<tr>
						<th>商品名称</th>
						<th>商品编号</th>
						<th>单价</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
					<c:forEach items="${order.orderItemList }" var="orderItem">
						<tr style="padding-top: 20px; padding-bottom: 20px;">
							<td width="400px">
								<div class="bookname">
								  <img align="middle" width="70" src="${orderItem.image_b }"/>
								  ${orderItem.bname }
								</div>
							</td>
							<td align="center">${orderItem.bid }</td>
							<td align="center">
								<span>&yen;${orderItem.currPrice }</span>
							</td>
							<td align="center">
								<span>${orderItem.quantity }</span>
							</td>
							<td align="center">
								<span>&yen;${orderItem.subtotal }</span>
							</td>			
						</tr>
</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	<div style="margin-top:15px;">
		<input type="button" onclick="javascript:window.print()" value="打印" class="submit"/>
		<c:if test="${order.status eq 2 and btn eq 'deliver' }">
		<input id="deliver" type="button" onclick="javascript:window.location.href='<c:url value="/admin/order/deliver.do?oid=${order.oid }"/>'" value="发货" class="submit"/>
		</c:if>
		<c:if test="${order.status eq 1 and btn eq 'cancel' }">
		<input id="cancel" type="button" onclick="javascript:window.location.href='<c:url value="/admin/order/cancel.do?oid=${order.oid }"/>'" value="取消" class="submit"/>
		</c:if>
	</div>
</body>
</html>