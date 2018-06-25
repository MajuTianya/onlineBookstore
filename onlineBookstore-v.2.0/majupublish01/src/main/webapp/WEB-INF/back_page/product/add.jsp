<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>majupublish-add</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/jquery/jquery.datepick.css'/>">
	<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>
</head>
<style type="text/css">
.h2_ch a:hover, .h2_ch a.here {
    color: #FFF;
    font-weight: bold;
    background-position: 0px -32px;
    text-decoration: none;
}
.h2_ch a {
    float: left;
    height: 32px;
    margin-right: -1px;
    padding: 0px 16px;
    line-height: 32px;
    font-size: 14px;
    font-weight: normal;
    border: 1px solid #C5C5C5;
    background: url('/res/maju/img/admin/bg_ch.gif') repeat-x scroll 0% 0% transparent;
}
a {
    color: #06C;
    text-decoration: none;
}
</style>

<script type="text/javascript">
$(function(){
	var tObj;
	$("#tabs a").each(function(){
		if($(this).attr("class").indexOf("here") == 0){tObj = $(this)}
		$(this).click(function(){
			var c = $(this).attr("class");
			if(c.indexOf("here") == 0){return;}
			var ref = $(this).attr("ref");
			var ref_t = tObj.attr("ref");
			tObj.attr("class","nor");
			$(this).attr("class","here");
			$(ref_t).hide();
			$(ref).show();
			tObj = $(this);
		});
	});
	
	$("#publishtime").datepick({dateFormat:"yy-mm-dd"});
	$("#printtime").datepick({dateFormat:"yy-mm-dd"});
	
	$("#btn").addClass("btn1");
	$("#btn").hover(
		function() {
			$("#btn").removeClass("btn1");
			$("#btn").addClass("btn2");
		},
		function() {
			$("#btn").removeClass("btn2");
			$("#btn").addClass("btn1");
		}
	);
	
	$("#btn").click(function() {
		var bname = $("#bname").val();
		var currPrice = $("#currPrice").val();
		var price = $("#price").val();
		var discount = $("#discount").val();
		var author = $("#author").val();
		var press = $("#press").val();
		var pid = $("#pid").val();
		var cid = $("#cid").val();
		var image_w = $("#imgUrl9").val();
		var image_b = $("#imgUrl7").val();
		
		if(!bname || !currPrice || !price || !discount || !author || !press || !pid || !cid || !image_w || !image_b) {
			alert("图名、当前价、定价、折扣、作者、出版社、1级分类、2级分类、大图、小图都不能为空！");
			return false;
		}
		
		if(isNaN(currPrice) || isNaN(price) || isNaN(discount)) {
			alert("当前价、定价、折扣必须是合法小数！");
			return false;
		}
		$("#form").submit();
	});
	
});

//异步上传图片到服务器
/* function uploadPicture(tuIndex){
	//定义参数
	var options = {
			url:"/UploadServlet?method=uploadPicture&tuIndex="+tuIndex,
			dataType:"json",
			type:"post",
			success:function(data){
				//回掉两个路径
				//url
				//path
				$("#allImgUrl"+tuIndex).attr("src",data.url);
				$("#imgUrl"+tuIndex).val(data.path);
			},
			error:function(XMLResponse){
			alert(XMLResponse.responseText);
			}
	};
	//是用jquery.form方式
	$("#jvForm").ajaxSubmit(options);
} */

//异步上传小图到服务器
function uploadPicture(){
	//定义参数
	var options = {
			url:"/admin/upload/uploadPicture.do",
			dataType:"json",
			type:"post",
			success:function(data){
				//回掉两个路径
				//url
				//path
				$("#allImgUrl7").attr("src",data.url);
				$("#imgUrl7").val(data.path);
			},
			error:function(XMLResponse){
			alert(XMLResponse.responseText);
			}
	};
	//是用jquery.form方式
	$("#jvForm").ajaxSubmit(options);
}

//异步上传大图到服务器
function uploadPictureW(){
	//定义参数
	var options = {
			url:"/admin/upload/uploadPictureW.do",
			dataType:"json",
			type:"post",
			success:function(data){
				//回掉两个路径
				//url
				//path
				$("#allImgUrl9").attr("src",data.url);
				$("#imgUrl9").val(data.path);
			},
			error:function(XMLResponse){
			alert(XMLResponse.responseText);
			}
	};
	//是用jquery.form方式
	$("#jvForm").ajaxSubmit(options);
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
		url:"/admin/product/ajaxFindChildren.do?pid="+pid,
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
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 商品管理 - 添加</div>
	<form class="ropt">
		<input type="button" onclick="javascript:history.go(-1);" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<h2 class="h2_ch"><span id="tabs">
<a href="javascript:void(0);" ref="#tab_1" title="基本信息" class="here">基本信息</a>
<a href="javascript:void(0);" ref="#tab_2" title="作品描述" class="nor">出版信息</a>
<a href="javascript:void(3);" ref="#tab_3" title="商品参数" class="nor">包装清单</a>
</span></h2>
<div class="body-box" style="float:right">
	<form id="jvForm" action="<c:url value='/admin/product/add.do'/>" method="post" enctype="multipart/form-data">
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody id="tab_1">
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						书名:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="bname" id="bname" maxlength="100" size="100"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						当前价:</td><td width="80%" class="pn-fcontent" >
						<input type="text" class="required" name="currPrice" id="currPrice" maxlength="10" style="width:50px;"/>元
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						定价:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="price" id="price" maxlength="10" style="width:50px;"/>元
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						折扣:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="discount" id="discount" maxlength="10" style="width:30px;"/>折
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						所属一级分类:</td><td width="80%" class="pn-fcontent">
								<select  name="pid" id="pid" onchange="loadChildren()">
									<option value="">-------请选择-------</option>
									<c:forEach items="${parents }" var="parent">
			    						<option value="${parent.cid }">${parent.cname }</option>
									</c:forEach>
								</select>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						所属二级分类:</td><td width="80%" class="pn-fcontent">
								<select name="cid" id="cid">
									<option value="">-------请选择-------</option>
								</select>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
					<span class="pn-frequired">*</span>
					上传小图:</td>
					<td width="80%" class="pn-fcontent">
						<img width="80" height="80" id="allImgUrl7"/>
						<input type="hidden" name="image_b" id="imgUrl7"/>
						<input type="file" onchange="uploadPicture()" name="imageb" id="image_b"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
					<span class="pn-frequired">*</span>
					上传大图:</td>
					<td width="80%" class="pn-fcontent">
						<img width="100" height="100" id="allImgUrl9"/>
						<input type="hidden" name="image_w" id="imgUrl9"/>
						<input type="file" onchange="uploadPictureW()" name="imagew" id="imagew"/>
					</td>
				</tr>
			</tbody>
			<tbody id="tab_2" style="display: none">
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						作者:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="author" id="author" maxlength="100" size="100" style="width:150px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						出版社:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="press" id="press" maxlength="100" size="100" style="width:200px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						出版时间:</td><td width="80%" class="pn-fcontent">
						<input type="text" readonly="readonly" class="required" name="publishtime" id="publishtime" maxlength="100" size="100" style="width:150px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						版次:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="edition" maxlength="100" size="100" style="width:40px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						页数:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="pageNum" maxlength="100" size="100" style="width:50px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						字数:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="wordNum" maxlength="100" size="100" style="width:80px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						印刷时间:</td><td width="80%" class="pn-fcontent">
						<input type="text" readonly="readonly" class="required" name="printtime" id="printtime" maxlength="100" size="100" style="width:100px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						开本:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="booksize" maxlength="100" size="100" style="width:30px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						纸张:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="paper" maxlength="100" size="100" style="width:80px;"/>
					</td>
				</tr>
			</tbody>
			<tbody id="tab_3" style="display: none">
				<tr >
					<td width="100%" align="center" colspan="2">
						<textarea rows="15" cols="136" id="productList" name="bookList"></textarea>
					</td>
				</tr>
			</tbody>
			<tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" id="btn" class="submit" value="提交"/> &nbsp; <input type="reset" class="reset" value="重置"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>