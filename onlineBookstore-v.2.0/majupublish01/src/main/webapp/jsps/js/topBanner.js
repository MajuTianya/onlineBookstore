/**
 * 
 */
$(function(){
	$("li a").click(function(){
		$(".set").removeClass();
		$(this).addClass("set");
	});
	
	/**
	 * 为search添加hover时间
	 */
	$("#search img").hover(function(){
		$(this).attr("src","/images/search2.png");
	},function(){
		$(this).attr("src","/images/search.png");
	});
	
}); 