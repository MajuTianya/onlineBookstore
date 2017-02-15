<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/back_page/head.jsp" %>
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
    background: url('/majupublish/res/maju/img/admin/bg_ch.gif') repeat-x scroll 0% 0% transparent;
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
	
	
});
</script>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 商品管理 - 查看</div>
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
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody id="tab_1">
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						书名:</td><td width="80%" class="pn-fcontent">
						<input type="text" readonly="readonly" value="${book.bname }" name="bname" id="bname" maxlength="100" size="100"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						当前价:</td><td width="80%" class="pn-fcontent" >
						<input type="text" readonly="readonly" value="${book.currPrice }" name="currPrice" id="currPrice" maxlength="10" style="width:50px;"/>元
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						定价:</td><td width="80%" class="pn-fcontent">
						<input type="text" readonly="readonly" value="${book.price }" name="price" id="price" maxlength="10" style="width:50px;"/>元
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						折扣:</td><td width="80%" class="pn-fcontent">
						<input type="text" readonly="readonly" value="${book.discount }" name="discount" id="discount" maxlength="10" style="width:30px;"/>折
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						所属一级分类:</td><td width="80%" class="pn-fcontent">
								<input type="text" readonly="readonly" value="${book.category.parent.cname }" name="pid" id="pid" maxlength="10" style="width:200px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						所属二级分类:</td><td width="80%" class="pn-fcontent">
								<input type="text" readonly="readonly" value="${book.category.cname }" name="cid" id="cid" maxlength="10" style="width:200px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
					<span class="pn-frequired">*</span>
					上传小图:</td>
					<td width="80%" class="pn-fcontent">
						<img width="80" height="80" id="allImgUrl7" src="<c:url value='/${book.image_b }'/>" />
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
					<span class="pn-frequired">*</span>
					上传大图:</td>
					<td width="80%" class="pn-fcontent">
						<img width="100" height="100" id="allImgUrl9" src="<c:url value='/${book.image_w }'/>"/>
					</td>
				</tr>
			</tbody>
			<tbody id="tab_2" style="display: none">
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						作者:</td><td width="80%" class="pn-fcontent">
						<input type="text" readonly="readonly" value="${book.author }" name="author" id="author" maxlength="100" size="100" style="width:150px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						出版社:</td><td width="80%" class="pn-fcontent">
						<input type="text" readonly="readonly" value="${book.press }" name="press" id="press" maxlength="100" size="100" style="width:200px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						出版时间:</td><td width="80%" class="pn-fcontent">
						<input type="text" readonly="readonly" value="${book.publishtime }" name="publishtime" id="publishtime" maxlength="100" size="100" style="width:150px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						版次:</td><td width="80%" class="pn-fcontent">
						<input type="text" readonly="readonly" value="${book.edition }" name="edition" maxlength="100" size="100" style="width:40px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						页数:</td><td width="80%" class="pn-fcontent">
						<input type="text" readonly="readonly" value="${book.pageNum }" name="pageNum" maxlength="100" size="100" style="width:50px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						字数:</td><td width="80%" class="pn-fcontent">
						<input type="text" readonly="readonly" value="${book.wordNum }" name="wordNum" maxlength="100" size="100" style="width:80px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						印刷时间:</td><td width="80%" class="pn-fcontent">
						<input type="text" readonly="readonly" value="${book.printtime }" name="printtime" id="printtime" maxlength="100" size="100" style="width:100px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						开本:</td><td width="80%" class="pn-fcontent">
						<input type="text" readonly="readonly" value="${book.booksize }" name="booksize" maxlength="100" size="100" style="width:30px;"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						纸张:</td><td width="80%" class="pn-fcontent">
						<input type="text" readonly="readonly" value="${book.paper }" name="paper" maxlength="100" size="100" style="width:80px;"/>
					</td>
				</tr>
			</tbody>
			<tbody id="tab_3" style="display: none">
				<tr >
					<td width="100%" align="center" colspan="2">
						<textarea readonly="readonly" rows="15" cols="136" id="productList" name="bookList">${book.bookList }</textarea>
					</td>
				</tr>
			</tbody>
		</table>
</div>
</body>
</html>