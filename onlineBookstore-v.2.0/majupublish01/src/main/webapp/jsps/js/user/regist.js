/**
 * 
 */
$(function(){
	/*
	 * 1、得到所有的错误信息，循环遍历之。调用一个方法来确定是否显示错误信息(这里是由于隐藏错误信息的叉)
	 */
	$(".errorClass").each(function(){
		showError($(this));//遍历每个元素，
	});
	
	/*1、切换注册按钮图片
	 */
	$("#submitBtn").hover(
			function(){
				$("#submitBtn").attr("src","/images/regist2.png");
			},
			function(){
				$("#submitBtn").attr("src","/images/regist.png");
			});
	/*
	 * <input type="img" src=""/>和<input type="submit" value=""/>功能是一样的，所以下面这些是不用写的
	 * 
	 * 
	 * 2、给注册按钮添加submit()事件，完成表单校验
	$("#submitBtn").submit(function(){
		var bool=true;
		$(".inputClass").each(function(){
			var inputName=$(this).attr("name");//获取属性值
			bool=invokeValidateFunction(inputName);
		});  
		return bool;
	});
	 */
	
	/*
	 * 3、输入框得到焦点时隐藏错误信息
	 */
	$(".inputClass").focus(function(){
		var inputName=$(this).attr("name");
		$("#"+inputName+"Error").css("display","none");
	});
	
	/*
	 * 4、输入框失去焦点时进行校验
	 */
	$(".inputClass").blur(function(){
		var inputName=$(this).attr("name");
		invokeValidateFunction(inputName);
	});
	
	/*
	 * 5、表单提交时校验
	 */
	$("#registForm").submit(function(){
		var bool=true;
		$(".inputClass").each(function(){
			var inputName=$(this).attr("name");//获取属性值
			bool=invokeValidateFunction(inputName);
		});  
		return bool;
		});
});

/*
 * 输入对应的input名称，调用对应的validate方法。
 * 例如input名称为：loginname,那么调用validateLoginname()方法。
 */
function invokeValidateFunction(inputName){//loginname
	inputName=inputName.substring(0,1).toUpperCase()+inputName.substring(1);//inputName=Loginname
	var functionName="validate"+inputName;
	return eval(functionName+"()");//将字符串转化为函数执行eval(functionName+"()")==functionName()==validateLoginname()
}

/*
 * 校验登录名
 */
function validateLoginname(){
	var bool=true;
	$("#loginnameError").css("display","none");
	var value=$("#loginname").val();//获得元素
	if(!value){//非空校验
		 $("#loginnameError").css("display","");
		 $("#loginnameError").text("用户名不能为空！");
		 bool=false;
		}else if(value.length<3||value.length>20){//长度校验
			$("#loginnameError").css("display","");
			$("#loginnameError").text("用户名长度必须在3~20之间！");
			bool=false;
		}else{//是否被注册
			$.ajax({
				cache:false,
				async:false,
				type:"POST",
				dataType:"json",
				data:{method:"validateLoginname",loginname:value},
				url:"/goods/UserServlet",
				success:function(flag){//
					if(flag){
						$("#loginnameError").css("display","");
						$("#loginnameError").text("该用户名已被注册！");
						bool=false;
					}
				}
			});
			
		}
	return bool;
}

/*
 * 校验密码
 */
function validateLoginpass(){
	var bool=true;
	$("#loginpassError").css("display","none");
	var value=$("#loginpass").val();
	if(!value){//非空校验
		$("#loginpassError").css("display","");
		$("#loginpassError").text("密码不能为空！");
		bool=false;
	}else if(value.length<3||value.length>20){
		//长度校验
		$("#loginpassError").css("display","");
		$("#loginpassError").text("密码长度必须在3~20之间！");
		bool=false;
	}
	return bool;
}

/*
 * 校验确认密码
 */
function validateReloginpass(){
	var bool=true;
	$("#reloginpassError").css("dispaly","none");
	var value=$("#reloginpass").val();
	if(!value){
		$("#reloginpassError").css("display","");
		$("#reloginpassError").text("确认密码不能为空！");
		bool=false;
	}else if(value.length<3||value.length>20){
		$("#reloginpassError").css("display","");
		$("#reloginpassError").text("确认密码长度必须在3~20之间！");
		bool=false;
	}else if(value!=$("#loginpass").val()){
		$("#reloginpassError").css("display","");
		$("#reloginpassError").text("两次输入密码不一致！");
		bool=false;
	}
	return bool;
}

/*
 * 校验邮件Email
 */
function validateEmail(){
	var bool=true;
	$("#emailError").css("dispaly","none");
	var value=$("#email").val();
	if(!value){
		$("#emailError").css("display","");
		$("#emailError").text("Email不能为空！");
		bool=false;
	}else if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value)){
		//格式校验
		$("#emailError").css("display","");
		$("#emailError").text("错误的Email格式！");
		bool=false;
	}else {
		$.ajax({
			cache:false,
			async:false,
			type:"POST",
			dataType:"json",
			data:{method:"validateEmail",email:value},
			url:"/goods/UserServlet",
			success:function(flag){
				if(flag){
					$("#emailError").css("display","");
					$("#emailError").text("该邮件已被注册！");
					bool=false;
				}
			}
		});
	}
	return bool;
}

/*
 * 校验验证码
 */
function validateVerifyCode(){
	var bool=true;
	$("#verifyCodeError").css("dispaly","none");
	var value=$("#verifyCode").val();
	if(!value){
		$("#verifyCodeError").css("display","");
		$("#verifyCodeError").text("验证码不能为空！");
		bool=false;
	}else if(value.length!=4){
		//长度校验
		$("#verifyCodeError").css("display","");
		$("#verifyCodeError").text("错误的验证码！");
		bool=false;
	}else {
		$.ajax({
			cache:false,
			async:false,
			type:"POST",
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
/*
 * 判断当前元素是否存在内容，如果存在显示，不存在不显示！
 */
function showError(ele){
	var text=ele.text();
	if(!text)
		ele.css("display","none");//隐藏元素
	else
		ele.css("display","");//显示元素
}

/*
 * 换一张方法
 */
function _hyz(){
	/*
	 * 1、获取<img>元素
	 * 2、重新设置他的src
	 * 3、使用毫秒来添加参数
	 */
	$("#imgVerifyCode").attr("src","/goods/VerifyCodeServlet?a="+new Date().getTime());
}
