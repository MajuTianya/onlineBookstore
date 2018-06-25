/**
 * 
 */
$(function(){
	/**
	 * 1、在登录按钮得到和失去焦点时切换图片 
	 * 		$().hover(function(){},function(){});
	 * 2、给登陆录按钮添加submit实践，完成表单校验
	 * 		-->把#msg置空
	 *		-->逐个循环.input
	 *			-->获取当前循环元素的name
	 *			-->通过validate+name()校验
	 *				-->validateLoginname():
	 *						非空校验？否，返回false;长度校验？否，返回false;
	 *				-->validateLoginpass():
	 *						非空校验？否，返回false;长度校验？否，返回false;
	 *				-->validateVerigyCode();
	 *						非空校验？否，返回false;长度校验？否，返回false;
	 * 3、输入框在得到焦点时隐藏错误信息
	 * 4、输入框在失去焦点时进行校验
	 * 5、提交表单时校验
	 */
	/*
	 * 1、在登录按钮得到和失去焦点时切换图片
	 */
	$("#loginBtn").hover(
			function(){
				$("#loginBtn").attr("src","/images/login2.png");
			},
			function(){
				$("#loginBtn").attr("src","/images/login.png");
			});
	
	/*
	 * 2、给登录按钮添加submit事件，完成表单校验
	 */
	$("#loginBtn").submit(function(){
		var bool=true;
		$("#msgError").text("");
		$(".inputClass").each(function(){
			var inputName=$(this).attr("name");//获取属性值
			bool=invokeValidateFunction(inputName);
		});  
		return bool;
	});
	
	/*
	 * 3、当输入框得到焦点时隐藏错误信息
	 */
	$(".inputClass").focus(function(){
		var inputName=$(this).attr("name");
		$("#"+inputName+"Error").css("display","none");
	});
	
	/*
	 * 4、当输入框失去焦点时进行校验
	 */
	$(".inputClass").blur(function(){
		var inputName=$(this).attr("name");
		invokeValidateFunction(inputName);
	});
	
	/*
	 * 5、提交表但单时校验
	 */
	$("#loginForm").submit(function(){
		var bool=true;
		$(".inputClass").each(function(){
			var inputName=$(this).attr("name");
			if(!invokeValidateFunction(inputName)){
				bool=false;
			}
		});
		return bool;
	});
	
});

/*
 * invokeVilidateFunction()根据inputname调用不同的vilidate+Input()函数
 */
function invokeValidateFunction(inputName){
	inputName=inputName.substring(0,1).toUpperCase()+inputName.substring(1);
	var functionName="validate"+inputName;
	return eval(functionName+"()");
}

/*
 * 各个functionName()的具体实现
 */
/*
 * vilidateLoginname()：登录名校验
 */
function validateLoginname(){
	var bool=true;
	$("#loginnameError").css("display","none");
	var loginname=$("#loginname").val();
	if(!loginname){
		$("#loginnameError").css("display","");
		$("#loginnameError").text("用户名不能为空！");
		bool=false;
	}else if(loginname.length<3||loginname.length>20){
		$("#loginnameError").css("display","");
		$("#loginnameError").text("用户名长度必须在3~20之间！");
		bool=false;
	}
	return bool;
}

/*
 * vilidateLoginpass()：密码校验
 */
function validateLoginpass(){
	var bool=true;
	$("#loginpassError").css("dispaly","none");
	var loginpass=$("#loginpass").val();
	if(!loginpass){
		$("#loginpassError").css("display","");
		$("#loginpassError").text("密码不能为空！");
		bool=false;
	}else if(loginpass.length<3||loginpass.length>20){
		$("#loginpassError").css("display","");
		$("#loginpassError").text("密码长度必须在3~20之间！");
		bool=false;
	}
	return bool;
}

/*
 * validateVerifyCode():验证码校验+异步校验
 */
function validateVerifyCode(){
	var bool=true;
	$("#verifyCodeError").css("display","none");
	var verifyCode=$("#verifyCode").val();
	if(!verifyCode){
		$("#verifyCodeError").css("display","");
		$("#verifyCodeError").text("验证码不能为空！");
		bool=false;
	}else if(verifyCode.length!=4){
		$("#verifyCodeError").css("display","");//这个一定要在的
		$("#verifyCodeError").text("验证码长度必须为4！");
		bool=false;
	}else{
		$.ajax({
			cache:false,
			async:false,
			type:"POST",
			dataType:"json",
			data:{method:"vilidateVerifyCode",verifyCode:verifyCode},
			url:"/goods/UserServlet",
			success:function(flag){
				if(!flag){
					$("#verifyCodeError").css("display","");
					$("#verifyCodeError").text("错误的验证码！");
					bool=false;
				}
			}
		});
	}
	return bool;
}

/*
 * 换一张
 */
function _hyz(){
	$("#imgVerifyCode").attr("src","/goods/VerifyCodeServlet?a="+new Date().getTime());
}
