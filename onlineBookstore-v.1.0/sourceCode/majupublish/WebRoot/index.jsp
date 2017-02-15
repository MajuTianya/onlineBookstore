<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>马驹'出版</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/index.css'/>"></link>
	<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/images/icon.ico'/>" media="screen" />
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsps/js/index.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jquery/jquery.lazyload.min.js'/>"></script>
	<script type="text/javascript" charset="utf-8">
  	$(function() {
      	$("img").lazyload({effect: "fadeIn"});
  	});
	</script>
  </head>
  
  <body>
  <div class="mjcbDiv">
  	<div class="topTitleDiv"><iframe scrolling="no" frameborder="0" src="<c:url value='/jsps/topTitle.jsp'/>" name="topTitleIframe" class="iframe"></iframe></div>
  	<div class="topBannerDiv"><iframe scrolling="no" frameborder="0" src="<c:url value='/jsps/topBanner.jsp'/>" name="topBannerIframe" class="iframe" ></iframe></div>
  	<div class="bodyDiv">
  		<iframe scrolling="no" frameborder="0" src="<c:url value='/BookServlet?method=findByTime'/>" name="bodyIframe" class="iframe"></iframe>
  		<div class="menuDiv"><iframe scrolling="no" frameborder="0" src="<c:url value='/CategoryServlet?method=findAll'/>" name="menuIframe" class="iframe" ></iframe></div>
  	</div>
  	<div class="bottomDiv"><iframe scrolling="no" frameborder="0" src="<c:url value='/jsps/bottom.jsp'/>" name="bottomIframe" class="iframe"></iframe></div>
  </div>
	<script type="text/javascript">
    //注意：下面的代码是放在和iframe同一个页面调用,放在iframe下面
    $(".iframe").load(function () {
        var mainheight = $(this).contents().find("body").height();
        $(this).height(mainheight);
    });
	</script>
  </body>
</html>
