/**
 * 
 */
$(function(){
	$(".listDiv ul li").hover(function(){
		$(this).css("border-left","1px solid #000");
	},function(){
		$(this).css("border-left","1px solid #f2f1e2");
	});
});