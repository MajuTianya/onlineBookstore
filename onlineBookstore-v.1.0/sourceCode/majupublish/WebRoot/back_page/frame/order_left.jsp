<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>product-left</title>
<link href="res/maju/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/theme.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="res/common/css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<!-- <script src="/thirdparty/ckeditor/ckeditor.js" type="text/javascript"></script> -->
<!-- <script src="/thirdparty/My97DatePicker/WdatePicker.js" type="text/javascript"></script> -->
<script src="res/common/js/jquery.js" type="text/javascript"></script>
<script src="res/common/js/jquery.ext.js" type="text/javascript"></script>
<script src="res/common/js/jquery.form.js" type="text/javascript"></script>
<script src="res/common/js/maju.js" type="text/javascript"></script>
<script src="res/maju/js/admin.js" type="text/javascript"></script>
</head>
<body class="lbody">
<div class="left">
<%@ include file="/back_page/date.jsp" %>
     <ul class="w-lful">
		<li><a target="rightFrame" href="<c:url value='/admin/AdminOrderServlet?method=findByStatus&status=1'/>">未付款</a></li>
		<li><a target="rightFrame" href="<c:url value='/admin/AdminOrderServlet?method=findByStatus&status=2'/>">已付款</a></li>
		<li><a target="rightFrame" href="<c:url value='/admin/AdminOrderServlet?method=findByStatus&status=3'/>">已发货</a></li>
		<li><a target="rightFrame" href="<c:url value='/admin/AdminOrderServlet?method=findByStatus&status=4'/>">交易成功</a></li>
		<li><a target="rightFrame" href="<c:url value='/admin/AdminOrderServlet?method=findByStatus&status=5'/>">已取消</a></li>
     </ul>
</div>
</body>
</html>