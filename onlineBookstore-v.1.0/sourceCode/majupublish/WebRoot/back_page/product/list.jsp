<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>majupublish-list</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/res/css/product/list.css'/>" />
<script type="text/javascript">
/**
 * 全选,通过this.checked控制其他checkbox与全选框状态一致。
 */
function checkBox(ids, checked){
	$("input[name='"+ids+"']").attr("checked",checked);
}
/**
 * 批量删除
 */
function optDelete(){
	var size = $("input[name='ids']:checked").size();
	var ids = $("input[name='ids']:checked").val();
	if(size <= 0 ||size == null){
		alert("您还未选择要删除的记录！");
		return ;
	}
	if(!confirm("您确定要删除吗？")){
		return ;
	}
	$("#jvForm").attr("action", "<c:url value='/admin/AdminBookServlet?method=deletes&ids="+ids+"'/>");
	$("#jvForm").attr("method", "post").submit();
}
function loadChildren() {
	/*
	1. 获取pid
	2. 发出异步请求，功能之：
	  3. 得到一个数组
	  4. 获取cid元素(<select>)，把内部的<option>全部删除
	  5. 添加一个头（<option>请选择2级分类</option>）
	  6. 循环数组，把数组中每个对象转换成一个<option>添加到cid中
	*/
	// 1. 获取pid
	var pid = $("#pid").val();
	// 2. 发送异步请求
	$.ajax({
		async:true,
		cache:false,
		url:"/majupublish/admin/AdminBookServlet",
		data:{method:"ajaxFindChildren", pid:pid},
		type:"POST",
		dataType:"json",
		success:function(arr) {
			// 3. 得到cid，删除它的内容
			$("#cid").empty();//删除元素的子元素
			$("#cid").append($("<option value=''>-------请选择-------</option>"));//4.添加头
			// 5. 循环遍历数组，把每个对象转换成<option>添加到cid中
			for(var i = 0; i < arr.length; i++) {
				var option = $("<option>").val(arr[i].cid).text(arr[i].cname);
				$("#cid").append(option);
			}
		}
	});
}
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 商品管理 - 列表</div>
	<form class="ropt">
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='<c:url value="/admin/AdminBookServlet?method=addPre"/>'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form method="get" id="tableForm" action="<c:url value='/admin/AdminBookServlet'/>" style="padding-top:5px;">
<input type="hidden" name="method" value="findByCombination"/>
书名: <input type="text" name="bname" value="${criteria.bname }"/>
作者：<input type="text" name="author" value="${criteria.author }"/>
出版社：<input type="text" name="press" value="${criteria.press }"/>
<br></br>
所属分类：一级
<select  name="pid" id="pid" onchange="loadChildren()">
	<option value="">-------请选择-------</option>
	<c:forEach items="${parents }" var="parent">
		<option value="${parent.cid }" <c:if test="${parent.cid eq criteriaPid }">selected="selected"</c:if>>${parent.cname }</option>
	</c:forEach>
</select>
二级
<select name="cid" id="cid">
	<c:if test="${empty criteria.category.cid}"><option value="">-------请选择-------</option></c:if>
	<c:if test="${!empty criteria.category.cid}"><option value="${criteria.category.cid}">${criteria.category.cname }</option></c:if>
</select>
	<input type="submit" class="query" value="查询"/>
	<input type="submit" class="query" value="重置"/>
<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20px"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th>
			<th width="260px">商品编号</th>
			<th>商品名称</th>
			<th width="10%">所属分类</th>
			<th width="4%">图片</th>
			<th width="5%">作者</th>
			<th width="5%">出版社</th>
			<th width="10%">操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
<c:forEach items="${pb.dataList }" var="book">
		<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
			<td align="center"><input type="checkbox" name="ids" value="${book.bid }"/></td>
			<td align="center">${book.bid }</td>
			<td align="center" class="bookname">${book.bname }</td>
			<td align="center">${book.category.cname }</td>
			<td align="center"><img width="50" height="50" src="<c:url value='/${book.image_b }'/>"/></td>
			<td align="center">${book.author }</td>
			<td align="center">${book.press }</td>
			<td align="center">
			<a href="javascript:void(0);" class="pn-opt" onclick="javascript:window.location.href='<c:url value="/admin/AdminBookServlet?method=load&bid=${book.bid }"/>'">查看</a> | <a href="javascript:void(0);" class="pn-opt" onclick="javascript:window.location.href='<c:url value="/admin/AdminBookServlet?method=editPre&bid=${book.bid }"/>'">修改</a> | <a href="javascript:void(0);" onclick="if(!confirm('您确定删除吗？')) {return false;}else{javascript:window.location.href='<c:url value="/admin/AdminBookServlet?method=delete&bid=${book.bid }"/>'}" class="pn-opt">删除</a>
			</td>
		</tr>
</c:forEach>
	</tbody>
</table>
<%@ include file="/back_page/pager/pager.jsp" %>
<div style="margin-top:15px;"><input class="del-button" type="button" value="删除" onclick="optDelete();"/></div>
</form>
</div>
</body>
</html>