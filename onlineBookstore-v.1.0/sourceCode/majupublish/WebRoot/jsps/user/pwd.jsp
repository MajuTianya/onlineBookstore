<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>pwd.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/pwd.css'/>"></link>
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsps/js/user/pwd.js'/>"></script>

  </head>
  
  <body>
  <div id="divMain">
  <div id="divTop">
  	<span>修改密码</span>
  </div>
  <div id="divBody">
  <form action="<c:url value='/UserServlet'/>" method="post" id="pwdForm" >
  <input type="hidden" name="method" value="updateLoginpass"/>
  	<table>
  		<tr>
  			<td><lable class="errorClass" id="msgError" >${msgError }</lable></td>
  			<!-- 回显在lable中的信息不能<lable class="errorClass" id="msgError" value="${msgError }"></lable> -->
  			<td>&nbsp;</td>
  			<td></td>
  		</tr>
  		<tr>
  			<td class="tdClass">原密码：</td>
  			<td><input class="inputClass" type="password" name="loginpass" id="loginpass" value="${user.loginpass }"/></td>
  			<td><lable class="errorClass" id="loginpassError" >${error.loginpass }</lable></td>
  		</tr>
  		<tr>
  			<td class="tdClass">新密码：</td>
  			<td><input class="inputClass" type="password" name="newloginpass" id="newloginpass" value="${user.newloginpass }"/></td>
  			<td><lable class="errorClass" id="newloginpassError" >${error.newloginpass }</lable></td>
  		</tr>
  		<tr>
  			<td class="tdClass">确认密码：</td>
  			<td><input class="inputClass" type="password" name="reloginpass" id="reloginpass" value="${user.reloginpass }"></td>
  			<td><label class="errorClass" id="reloginpassError" >${error.reloginpass }</label></td>
  		</tr>
  		<tr>
  			<td class="tdClass"></td>
  			<td><img src="<c:url value='/VerifyCodeServlet'/>" id="imgVerifyCode"/><a href="javaScript:_hyz()">看不清，换一张</a></td>
  			<td></td>
  		</tr>
  		<tr>
  			<td class="tdClass">验证码：</td>
  			<td><input class="inputClass" id="verifyCode" type="text" name="verifyCode" value="${user.verifyCode }"/></td>
  			<td><lable class="errorClass" id="verifyCodeError" >${error.verifyCode }</lable></td>
  		</tr>
  		<tr>
  			<td class="tdClass"></td>
  			<td><input id="pwdBtn" type="submit" value="修改密码"/></td>
  			<td></td>
  		</tr>
  	</table>
  	</form>
  </div>
  </div>
  </body>
</html>
