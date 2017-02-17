/**
 * 
 */
$(function(){
	/**
	 * 1、给修改密码按钮添加submit事件，并进行校验
	 * 2、当输入框得到焦点时隐藏错误信息
	 * 3、当输入框失去焦点时进行校验（需异步的异步，不需异步的不异步）
	 * 4、表单提交时进行校验
	 */
	
	/*
	 * 1、给修改按钮添加submit事件,进行校验
	 */
	$("#pwdBtn").submit(function(){
		var bool=true;
		$(".inputClass").each(function(){
			var inputName=$(this).attr("name");
			if(!invokeValidateFunction(inputName)){
				bool=false;
			};
		});
		return bool;
	});
	
	/*
	 * 2、当输入框得到焦点时隐藏错误信息
	 */
	$(".inputClass").focus(function(){
		var inputName=$(this).attr("name");
		$("#"+inputName+"Error").css("display","none");
	});
	
	/*
	 * 3、当输入框失去焦点的时候进行校验              
	 */
	$(".inputClass").blur(function(){
		var inputName=$(this).attr("name");
		invokeValidateFunction(inputName);
	});
	
	/*
	 * 4、当表单提交时进行校验
	 */
	$("#pwdForm").submit(function(){
		$("#msgError").text("");
		var bool=true;
		$(".inputClass").each(function(){
			var inputName=$(this).attr("name");
			if(!invokeValidateFunction(inputName)){
				bool=false;
			};
		});
		return bool;
	});
});

/*
 * 换一张
 */
function _hyz(){
	$("#imgVerifyCode").attr("src","/goods/VerifyCodeServlet?a="+new Date().getTime());
}

/*
 * invokeValidateFunction(inputName):通过元素名调用对应元素脚丫方法
 */
function invokeValidateFunction(inputName){
	inputName=inputName.substring(0,1).toUpperCase()+inputName.substring(1);
	var functionName="validate"+inputName;
	return eval(functionName+"()");
}

/*
 * validateLoginpass():对原密码进行校验
 */
function validateLoginpass(){
	$("#loginpasssError").css("display","none");
	var bool=true;
	var value=$("#loginpass").val();
	if(!value){
		$("#loginpassError").css("display","");
		$("#loginpassError").text("原密码不能为空！");
		bool=false;
	}else if(value.length<3||value.length>20){
		$("#loginpassError").css("display","");
		$("#loginpassError").text("原密码长度必须在3~20之间！");
		bool=false;
	}
	return bool;
}

/*
 * validateNewloginpass():对新密码进行校验
 */
function validateNewloginpass(){
	$("#newloginpassError").css("display","none");
	bool=true;
	var value=$("#newloginpass").val();
	if(!value){
		$("#newloginpassError").css("display","");
		$("#newloginpassError").text("新密码不能为空！");
		bool=false;
	}else if(value.length<3||value.length>20){
		$("#newloginpassError").css("display","");
		$("#newloginpassError").text("新密码长度必须在3~20之间！");
		bool=false;
	}
	return bool;
}

/*
 * validateReloginpass():对确认密码进行校验
 */
function validateReloginpass(){
	$("#reloginpassError").css("display","none");
	bool=true;
	var value=$("#reloginpass").val();
	if(!value){
		$("#reloginpassError").css("display","");
		$("#reloginpassError").text("确认密码不能为空！");
		bool=false;
	}else if(value.length<3||value.length>20){
		$("#reloginpassError").css("display","");
		$("#reloginpassError").text("确认密码长度必须在3~20之间！");
		bool=false;
	}else if(value!=$("#newloginpass").val()){
		$("#reloginpassError").css("display","");
		$("#reloginpassError").text("两次密码不一致！");
		bool=false;
	}
	return bool;
}

/*
 * validateVerifyCode():对验证码进行校验
 */
function validateVerifyCode(){
	$("#verifyCodeError").css("display","none");
	bool=true;
	var value=$("#verifyCode").val();
	if(!value){
		$("#verifyCodeError").css("display","");
		$("#verifyCodeError").text("验证码不能为空！");
		bool=false;
	}else if(value.length!=4){
		$("#verifyCodeError").css("display","");
		$("#verifyCodeError").text("验证码长度必须为4！");
		bool=false;
	}else {
		$.ajax({
			cache:false,
			async:false,
			type:"post",
			dataType:"json",
			data:{method:"validateVerifyCode",verifyCode:value},
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
