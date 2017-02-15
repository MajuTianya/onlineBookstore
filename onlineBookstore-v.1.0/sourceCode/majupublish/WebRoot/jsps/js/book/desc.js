/**
 * 
 */
$(function() {
	/*
	 * 1、给jian添加click事件
	 */
	$(".jian").click(function(){
		var quantity=Number($("#quantity").val());
		if(quantity>1){
			$("#quantity").val(quantity-1);
		}
	});
	
	/*
	 * 2、给jia添加click事件
	 */
	$("#jia").click(function(){
		var quantity=Number($("#quantity").val());
		$("#quantity").val(quantity+1);
	});
});