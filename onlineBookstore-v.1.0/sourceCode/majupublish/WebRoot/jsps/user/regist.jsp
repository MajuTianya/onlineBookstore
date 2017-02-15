<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>regist</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/regist.css'/>"></link>
	<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/images/icon.ico'/>" media="screen" />
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsps/js/user/regist.js'/>"></script>
  </head>
  
  <body>
     <div id="divMain">
     	<div style="float:left; "><a href="<c:url value='/index.jsp'/>" target="_parent"><img src="<c:url value='/images/logo.png'/>" title="马驹’出版" alt="logo"/></a></div>
    	<div id="contentDiv">
    	<div id="divTitle">
    		<span id="spanTitle">新用户注册</span>
    	</div>
    	<div id="divBody">
    	<form action="<c:url value='/UserServlet'/>" method="post" id="registForm">
    	<input type="hidden" name="method" value="regist"/>
    		<table id="tableForm">
    			<tr>
    				<td class="tdText">用户名：</td>
    				<td class="tdInput"><input class="inputClass" type="text" name="loginname" id="loginname" value="${user.loginname}"/></td>
    				<td class="tdError"><label class="errorClass" id="loginnameError">${errors.loginname}</label></td>
    			</tr>
    			<tr>
    				<td class="tdText">登录密码：</td>
    				<td><input class="inputClass" type="password" name="loginpass" id="loginpass" value="${user.loginpass}"/></td>
    				<td><label class="errorClass" id="loginpassError">${errors.loginpass }</label></td>
    			</tr>
    			<tr>
    				<td class="tdText">确认密码：</td>
    				<td><input class="inputClass" type="password" name="reloginpass" id="reloginpass" value="${user.reloginpass }"/></td>
    				<td><label class="errorClass" id="reloginpassError">${errors.reloginpass }</label></td>
    			</tr>
    			<tr>
    				<td class="tdText">Email：</td>
    				<td><input class="inputClass" type="text" name="email" id="email" value="${user.email }"/></td>
    				<td><label class="errorClass" id="emailError">${errors.email }</label></td>
    			</tr>
    			<tr>
    				<td class="tdText">验证码：</td>
    				<td><input class="inputClass" type="text" name="verifyCode" id="verifyCode" value="${user.verifyCode }"/></td>
    				<td><label class="errorClass" id="verifyCodeError">${errors.verifyCode}</label></td>
    			</tr>
    			<tr>
    				<td></td>
    				<td><div id="divVerifyCode"><img id="imgVerifyCode" src="<c:url value='/VerifyCodeServlet'/>"/></div></td>
    				<td><label><a href="javascript:_hyz()">换一张</a></label></td>
    			</tr>
    			<tr>
    				<td></td>
    				<td><input type="image" src="<c:url value='/images/regist.png'/>" id="submitBtn"/></td>
    				<td><label></label></td>
    			</tr>
    		</table>
    		</form>
    	</div>
    	</div>
    	
    	<div style="dipslay:inline-block; float:left; ">
    	<%@include file="/jsps/bottom.jsp" %>
    	</div>
    </div>
  </body>
</html>
