<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/back_page/head.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>管理员登录页面</title>
<script type="text/javascript">
function checkForm() {
	if(!$("#adminname").val()) {
		alert("管理员名称不能为空！");
		return false;
	}
	if(!$("#adminpwd").val()) {
		alert("管理员密码不能为空！");
		return false;
	}
	return true;
}
</script>
<style type="text/css">
	a{ color:#008EE3}
	a:link  { text-decoration: none;color:#008EE3}
	A:visited {text-decoration: none;color:#666666}
	A:active {text-decoration: underline}
	A:hover {text-decoration: underline;color: #0066CC}
	A.b:link {
		text-decoration: none;
		font-size:12px;
		font-family: "Helvetica,微软雅黑,宋体";
		color: #FFFFFF;
	}
	A.b:visited {
		text-decoration: none;
		font-size:12px;
		font-family: "Helvetica,微软雅黑,宋体";
		color: #FFFFFF;
	}
	A.b:active {
		text-decoration: underline;
		color: #FF0000;
	}
	A.b:hover {text-decoration: underline; color: #ffffff}
	
	.table1 {
		border: 1px solid #CCCCCC;
	}
	.font {
		font-size: 12px;
		text-decoration: none;
		color: #999999;
		line-height: 20px;
	}
	.input {
		font-size: 12px;
		color: #999999;
		text-decoration: none;
		border: 0px none #999999;
	}
	td {
		font-size: 12px;
		color: #007AB5;
	}
	form {
		margin: 1px;
		padding: 1px;
	}
	input {
		border: 0px;
		height: 26px;
		color: #007AB5;
	}
		.unnamed1 {
		border: thin none #FFFFFF;
	}
	.unnamed1 {
		border: thin none #FFFFFF;
	}
	select {
		border: 1px solid #cccccc;
		height: 18px;
		color: #666666;
	}
	.unnamed1 {
		border: thin none #FFFFFF;
	}
	body {
		background-repeat: no-repeat;
		background-color: #9CDCF9;
		background-position: 0px 0px;
	
	}
	.tablelinenotop {
		border-top: 0px solid #CCCCCC;
		border-right: 1px solid #CCCCCC;
		border-bottom: 0px solid #CCCCCC;
		border-left: 1px solid #CCCCCC;
	}
	.tablelinenotopdown {
	
		border-top: 1px solid #eeeeee;
		border-right: 1px solid #eeeeee;
		border-bottom: 1px solid #eeeeee;
		border-left: 1px solid #eeeeee;
	}
	.style6 {FONT-SIZE: 9pt; color: #7b8ac3; }
	</style>
</head>
<body bgcolor="#9cdcf9">
	<table width="681" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:120px">
	  <tr>
	    <td width="353" height="259" align="center" valign="bottom" background="/majupublish/res/maju/img/login/login_1.gif"><table width="318" border="0" cellspacing="3" cellpadding="0" height="20">
	      <tr>
	        <td align="center" valign="bottom" style="color:#05B8E4"><a href="<c:url value='/index.jsp'/>" target="_blank">马驹出版</a></td>
	      </tr>
	    </table></td>
	    <td width="195" background="/majupublish/res/maju/img/login/login_2.gif">
	      <form name="form1" method="post" action="<c:url value='/AdminServlet'/>">
	      <input type="hidden" name="method" value="login"/>
	      <table width="190" height="106" border="0" align="center" cellpadding="2" cellspacing="0">
	            <tr>
	              <td height="50" colspan="2" align="left" valign="middle"><br><br><br>
	              <br><br><br>
	            </td>
	            </tr>
	            <tr>
	            <td width="60" height="30" align="left"></td>
	            <td>
	              	<lable style="font-weight: 900; color: red">${msg }</lable>
	            </td>
	            </tr>
	            <tr>
	              <td width="60" height="30" align="left">登陆名称：</td>
	              <td><input name="adminname" type="text" style="background:url(/majupublish/res/maju/img/login/login_6.gif) repeat-x; border:solid 1px #27B3FE; height:20px; background-color:#FFFFFF" id="username"size="16"></td>
	            </tr>
	            <tr>
	              <td width="60" height="30" align="left">登陆密码：</td>
	              <td><input name="adminpwd" type="password" style="background:url(/majupublish/res/maju/img/login/login_6.gif) repeat-x; border:solid 1px #27B3FE; height:20px; background-color:#FFFFFF" id="pwd" size="16"></td>
	            </tr>
	            <tr>
	              <td height="30" colspan="2" align="center">&nbsp;</td>
	            </tr>
	            <tr>
	              <td colspan="2" align="center"><input type="submit" name="submit" style="background:url(/majupublish/res/maju/img/login/login_5.gif) no-repeat;width:70px;" value=" 登  陆 " >&nbsp;&nbsp;&nbsp;
				  <input type="reset" name="Submit" style="background:url(/majupublish/res/maju/img/login/login_5.gif) no-repeat;width:70px;" value=" 取  消 "></td>
	            </tr>
	            <tr>
	              <td height="5" colspan="2" align="center">
	              </td>
	            </tr>
	    	</table>
	    	</form>
	      </td>
	    <td width="133" background="/majupublish/res/maju/img/login/login_3.gif">&nbsp;</td>
	  </tr>
	  <tr>
	    <td height="161" colspan="3" background="/majupublish/res/maju/img/login/login_4.gif"></td>
	  </tr>
	</table>
	</body>
</html>
