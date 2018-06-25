/**
 * 
 */
$(function(){
	var index=0;
	var maxing=4;
	/**
	 * $("#bannerNav li").eq(index).addClass("current").siblings().removeClass("current");
	$("#bannerDiv img").first().animate({opacity:1},500,function(){
		$(this).animate({opacity:0.5},2450,function(){
			$(this).show().parent().appendTo($("#bannerDiv"));
		});
	});
	 */
	/*
	 * 滑动导航条改变内容
	 */
	$("#bannerNav li").hover(function(){
		if(myTime){
			clearInterval(myTime);
		}
		index=$("#bannerNav li").index(this);
		myTime=setInterval(function(){
			showFlash(index);
			$("#bannerDiv").stop();
		},3000);//每次调用暂停四百秒
	},function(){
		clearInterval(myTime);
		myTime=setInterval(function(){
			showFlash(index);
			index++;
			if(index==maxing){
				index=0;
			}
		},3000);
	});
	/*
	 * 滑入停住动画，划出开始动画
	 */
	$("#bannerDiv").hover(function(){
		if(myTime){
			clearInterval(myTime);
		}
	},function(){
		myTime=setInterval(function(){
			showFlash(index);
			index++;
			if(index==maxing){
				index=0;
			}
		},3000);
	});
	
	/*
	 * 自动播放
	 */
	var myTime=setInterval(function(){
		showFlash(index);
		index++;
		if(index==maxing){
			index=0;
		};
	},3000);
	
	/**
	 * content设置
	 */
	/*
	 * 为go设置hover事件
	 */
	$(".content dd img").hover(function(){
		$(this).attr("src","/images/bt_buy2.png");
	},function(){
		$(this).attr("src","/images/bt_buy.jpg");
	});
	
	/*
	 * 为content设置hover事件
	 */
	$(".content ul li").hover(function(){
		$(this).css("border-left","1px solid #000");
	},function(){
		$(this).css("border-left","1px solid #f2f1e2");
	});
	
});

function showFlash(i){
	$("#bannerDiv div").eq(i).animate({opacity: 1},1000).css({"z-index": "1"}).siblings().animate({opacity: 0},1000).css({"z-index": "0"});
	$("#bannerNav li").eq(i).addClass("current").siblings().removeClass("current");
	
}