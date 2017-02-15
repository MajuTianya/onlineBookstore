<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>majupublish-list</title>
<script>
$(function(){
	$("#pid").change(function(){
		$("#nameStr").val($("#pid option:selected").text());
	});
});
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 分类管理 - 列表</div>
	<form class="ropt">
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='<c:url value="/admin/AdminCategoryServlet?method=addPre"/>'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form action="<c:url value='/admin/AdminCategoryServlet?method=findCategoryByCategories'/>" method="post" style="padding-top:5px;">
类别ID: <input type="text" name="cid" value="${category.cid }"/>
类别名称: <input type="text" name="cname" value="${category.cname }"/>
<input type="hidden" id="nameStr" name="nameStr" value=""/>
所属分类：<select name="pid" id="pid" >
<option value="">无</option>
<c:forEach items="${parentss }" var="parent">
<option value="${parent.cid }" <c:if test="${parent.cid eq category.parent.cid }">selected="selected"</c:if> >${parent.cname }</option>

</c:forEach>
</select>
<input type="submit" class="query" value="查询"/>
</form>
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th>类别ID</th>
			<th>类别名称</th>
			<th>所属分类</th>
			<th>类别描述</th>
			<th>操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
	<c:forEach items="${parents }" var="parent">    
		<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">
			<td align="center">${parent.cid }</td>
			<td align="center">${parent.cname }</td>
			<td align="center">
			<c:if test="${!empty parent.parent }">
				${nameString }
			</c:if>
			</td>
			<td align="center">${parent.descr }</td>
			<td align="center">
			<a class="pn-opt" href="<c:url value='/admin/AdminCategoryServlet?method=editPre&cid=${parent.cid }'/>">修改</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}" href="<c:url value='/admin/AdminCategoryServlet?method=deleteParent&cid=${parent.cid }'/>">删除</a>
			</td>
		</tr>	
   		<c:forEach items="${parent.children }" var="child">
    	<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">
			<td align="center">${child.cid }</td>
			<td align="center">${child.cname }</td>
			<td align="center">${parent.cname }</td>
			<td align="center">${child.descr }</td>
			<td align="center">
			<a class="pn-opt" href="<c:url value='/admin/AdminCategoryServlet?method=editPre&cid=${child.cid }'/>">修改</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}" href="<c:url value='/admin/AdminCategoryServlet?method=deleteChild&cid=${child.cid }'/>">删除</a>
			</td>
		</tr>
   		</c:forEach>
	</c:forEach>
	</tbody>
</table>
</div>
</body>
</html>