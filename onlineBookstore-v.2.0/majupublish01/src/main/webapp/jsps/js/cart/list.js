/**
 * 
 */
$(function(){
	/*
	 * 1、显示总计
	 * 	设置total
	 * 	获取每条选中条目的小计，循环相加
	 * 	给合计设置保留一位小数
	 */
	showTotal();
	
	/*
	 * 2、给全选按钮添加click事件
	 * 	设置全选的状态
	 * 	使各个选框同步
	 * 	实结算按钮同步
	 * 	显示计算
	 */
	$("#selectAll").click(function(){
		var bool=$("#selectAll").attr("checked");
		setItemCheckBox(bool);
		setJiesuan(bool);
		showTotal();
	});
	
	/*
	 * 3、给各个复选框添加click事件
	 * 	获取所有复选框的个数all;获取所有选中复选框的个数select
	 * 	比较二者的区别，根据情况设定全选按钮与结算按钮的状态
	 * 	显示计算
	 */
	$(":checkbox[name=checkboxBtn]").click(function(){
		var all=$(":checkbox[name=checkboxBtn]").length;
		var select=$(":checkbox[name=checkboxBtn][checked=true]").length;
		if(select==0){
			$("#selectAll").attr("checked",false);
			setJiesuan(false);
		}else if(select<all){
			$("#selectAll").attr("checked",false);
			setJiesuan(true);
		}else{
			$("#selectAll").attr("checked",true);
			setJiesuan(true);
		}
		showTotal();
	});
	
	/*
	 * 4、给jian添加click事件
	 * 	获取jian的id前缀，即该购物车的id，即该条目其他id前缀
	 * 	该条目quantity的值
	 * 	判断quantity的值，等于一，删除该条目；否则异步更新数据库
	 */
	$(".jian").click(function(){
		var id=$(this).attr("id").substring(0,32);
		var quantity=Number($("#"+id+"Quantity").val());
		if(quantity==1){
			if(confirm("您是否真确认该删除该条目？")){
				location="/goods/CartItemServlet?method=batchDelete&cartItemIds="+id;
			}
		}else{
			updateQuantity(id,quantity-1);
		}
	});
	
	/*
	 * 5、给jia添加click事件
	 */
	$(".jia").click(function(){
		var id=$(this).attr("id").substring(0,32);
		var quantity=Number($("#"+id+"Quantity").val());
		updateQuantity(id,quantity+1);
	});
	
});

/*
 * 显示总计
 */
function showTotal(){
	var total=0;
	$(":checkbox[name=checkboxBtn][checked=true]").each(function(){
		//获取input的var,即该条目其他id的前缀
		var id=$(this).val();
		var text=$("#"+id+"Subtotal").text();
		total+=Number(text);
	});
	$("#total").text(round(total,2));
}

/*
 * 将numb保留dec位小数，并四舍五入
 */
function round(numb, dec){
	//将numb转化为字符串
	var nums=numb+"";
	//获取小数点的位置index
	var index=nums.indexOf(".");
	//判断小数点是否存在，不存在，直接返回，存在，获取小数部分
	if(index<0){
		return numb;
	}
	//获取小数后的位数
	var n=nums.length-index-1;
	//判断小数点位数与精确位数的关系，小，直接返回；大，乘以dec*10，四舍五入，再变小数
	if(n<dec){
		return numb;
	}else{
		var a=Math.pow(10, dec);
		numb=numb*a;
		numb=Math.round(numb);//得到的是整数
		return numb/a;
	}
}

/*
 * 设置各个checkBox的是否勾选
 */
function setItemCheckBox(bool){
	$(":checkbox[name=checkboxBtn]").attr("checked",bool);
}

/*
 * 同步结算按钮
 */
function setJiesuan(bool){
	if(bool){
		$("#jiesuan").css("background-position","0px -35px");
		$("#jiesuan").unbind("click");
	}else{
		$("#jiesuan").css("background-position","0px -480px");
		$("#jiesuan").click(function(){return false;});
	}
}

/*
 * 异步校验：updateQuantity(id,quantity)
 */
function updateQuantity(id,quantity){
	$.ajax({
		async:false,
		cache:false,
		data:{method:"updateQuantity",cartItem:id,quantity:quantity},
		dataType:"json",
		type:"POST",
		url:"/goods/CartItemServlet",
		success:function(result){
			//设置页面quanitty的值
			$("#"+id+"Quantity").val(result.quantity);
			//设置页面小计subtotal
			$("#"+id+"Subtotal").text(result.subtotal);
			//设置页面总计total
			showTotal();
		}
	});
}

/*
 * 批量删除
 */
function _batchDelete(){
		var cartItemIds=new Array();
		$(":checkbox[name=checkboxBtn][checked=true]").each(function(){
			var id=$(this).val();
			cartItemIds.push(id);
		});
		if(cartItemIds.length!=0){
			if(confirm("您确定要删除这些条目？")){
				location="/goods/CartItemServlet?method=batchDelete&cartItemIds="+cartItemIds.toString();
			}
		}else{
			alert("您还没有选中条目！");
		}
	
}

/*
 * 批量加载
 */
function _loadCartItem(){
	var cartItemIds=new Array();
	$(":checkbox[name=checkboxBtn][checked=true]").each(function(){
		var id=$(this).val();
		cartItemIds.push(id);
	});
	$("#cartItemIds").val(cartItemIds.toString());
	$("#totalSubmit").val($("#total").text());
	$("#submitForm").submit();
}
