<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	//记住账号
	function setCookie(name,value,iDay)
	{
		var oDate=new Date();
		oDate.setDate(oDate.getDate()+iDay);
		document.cookie=name+'='+value+';expires='+oDate;

	}
	function getCookie(name)
	{
		var arr=document.cookie.split('; ');
		var i=0;
		for(i=0;i<arr.length;i++)
		{
			var arr2=arr[i].split('=');
			
			if(arr2[0]==name)
			{
				return arr2[1];
			}
		}
		
		return '';

	}
	function removeCookie(name)
	{
		setCookie(name, '1', -1);
	}
	
	//写一个函数：当remember为true时触发
	function rememberUser()
	{
		var nUser=document.getElementById('userCodeText');
		var nPass=document.getElementById('userPasswordText');
		var nRember=document.getElementById('remember');
		if(nRember.checked==true){
			setCookie('user',nUser.value,30);
			setCookie('pass',nPass.value,30);
		}
		
	}
	window.onload=function(){
		var oUser = getCookie('user');
		var oPass=getCookie('pass');
		if(oUser != null && oPass != null ){
			//设置userCodeText的值为oUser
			//设置userPasswordText为oPass
			//设置remember的值为true
		}
	}
	
	</script>

  </head>
  
  <body>
    <form>
					<label><span class="use">　　</span><input type="text" id="userCodeText" class="userCodeText" name="user"   placeholder="请输入用户名/账号" /></label>
					<label><span class="paw">　　</span><input type="password" id="userPasswordText" class="userPasswordText" name="pass" placeholder="请输入密码" /></label>
					<label style="border:none;"><input type="checkbox" id="remember" class="remember" onclick="rememberUser()"/>记住账号</label>
					<input type="button" id="loginBtn" class="loginBtn" value="登&nbsp;录" />
					<p>济南易贸创想软件有限公司提供技术支持</p>
	</form>		

  </body>
</html>
