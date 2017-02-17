<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>login</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/login.css'/>"></link>
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsps/js/user/login.js'/>"></script>
	
	<script type="text/javascript">
	$(function(){
		   var loginname=window.decodeURI("${cookie.loginname.value}");
		   if("${requestScope.user.loginname}"){ 
			  loginname="${requestScope.user.loginname}";
			  }
		   $("#loginname").val(loginname);
	   });
	</script>

  </head>
  
  <body>
    <div id="divMain">
   		<div style="float:left; "><a href="<c:url value='/index.jsp'/>" target="_parent"><img src="<c:url value='/images/logo.png'/>" title="马驹’出版" alt="logo"/></a></div>
   		<div id="contentDiv">
   		<div id="contentDiv2">
   		<div id="divLeft"><img id="imgZj" src="<c:url value='/images/zj.jpg'/>" alt="leftImage"/></div>
   		<div id="divRight">
   			<div id="divTop">
   				<span id="loginTop">用户登录</span>
   				<span ><a href="<c:url value='/jsps/user/regist.jsp'/>" id="registBtn" target="_top"></a></span>
   			</div>
   			<div>
   			<form action="<c:url value='/UserServlet'/>" method="post" id="loginForm" >
   			<input type="hidden" name="method" value="login"/>
   				<table>
   					<tr>
   						<td width="50" height="20"></td>
   						<td><lable class="errorClass" id="msgError">${msgError}</lable></td>
   					</tr>
   					<tr>
   						<td>用户名</td>
   						<td ><input class="inputClass" type="text" name="loginname" id="loginname" /></td>
   					</tr>
   					<tr>
   						<td height="20"></td>
   						<td><lable class="errorClass" id="loginnameError" >${error.loginnameError }</lable></td>
   					</tr>
   					<tr>
   						<td>密 码</td>
   						<td><input class="inputClass" type="password" name="loginpass" id="loginpass" value="${user.loginpass}"/></td>
   					</tr>
   					<tr>
   						<td height="20"></td>
   						<td><lable class="errorClass" id="loginpassError" >${error.loginpassError }</lable></td>
   					</tr>
   					<tr>
   						<td>验证码</td>
   						<td><input class="inputClass" type="text" name="verifyCode" id="verifyCode" value="${user.verifyCode}"/><img  src="<c:url value='/VerifyCodeServlet'/>" id="imgVerifyCode"/><a href="javaScript:_hyz()">换张图</a></td>
   					</tr>
   					<tr>
   						<td height="20"></td>
   						<td><lable class="errorClass" id="verifyCodeError" >${error.verifyCodeError }</lable></td>
   					</tr>
   					<tr>
   						<td></td><!--<img id="loginBtn" src="<c:url value='/images/login1.jpg'/>"> 这样的提交按钮是不行的 -->
   						<td><input type="image"  id="loginBtn" src="<c:url value='/images/login.png'/>"/></td>
   					</tr>
   				</table>
   				</form>
   			</div>
   		</div>
   		</div>
   	</div>
   	<div style="dipslay:inline-block; float:left; ">
    	<%@include file="/jsps/bottom.jsp" %>
    </div>
   	</div>
  </body>
</html>
