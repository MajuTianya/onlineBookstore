<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>majupublish-edit</title>
<script type="text/javascript" src="<c:url value='res/js/jquery.js' />"></script>
<script type="text/javascript">
		function checkForm() {
			if(!$("#cname").val()) {
				alert("分类名不能为空！");
				return false;
			}
			if(!$("#desc").val()) {
				alert("分类描述不能为空！");
				return false;
			}
			return true;
		}
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 分类管理 - 修改</div>
	<form class="ropt">
		<input type="button" onclick="javascript:history.go(-1);" value="返回列表" class="return-button"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box" style="float:right">
	<form id="jvForm" action="<c:url value='/admin/AdminCategoryServlet'/>" method="post" onsubmit="return checkForm()">
		<input type="hidden" name="method" value='edit'/>
		<input type="hidden" name="cid" value="${child.cid }"/>
		<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
			<tbody>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						<span class="pn-frequired">*</span>
						分类名称:</td><td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="cname" id="cname" maxlength="100" value="${child.cname }"/>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						所属分类:</td><td width="80%" class="pn-fcontent">
						<select name="pid" id="pid">
						<option value="">无</option>
						<c:forEach items="${parents }" var="parent">
							<c:if test="${child.cid  ne parent.cid }">
    						<option value="${parent.cid }" <c:if test="${parent.cid eq child.parent.cid }">selected="selected"</c:if>>${parent.cname }</option>
    						</c:if>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td width="20%" class="pn-flabel pn-flabel-h">
						分类描述:</td><td width="80%" class="pn-fcontent">
						<textarea class="required" name="descr" id="desc" rows="5" cols="50" >${child.descr }</textarea>
					</td>
				</tr>
			</tbody>
			<tbody>
				<tr>
					<td class="pn-fbutton" colspan="2">
						<input type="submit" class="submit" value="提交"/> &nbsp; <input type="reset" class="reset" value="重置"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>
</html>