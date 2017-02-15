<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>马驹出版-后台首页</title>
</head>
<frameset rows="72,*" frameborder="0" border="0" framespacing="0">
	<frame src="back_page/top.jsp" name="topFrame" noresize="noresize" id="leftFrame" />
	<frame src="back_page/main.jsp" name="mainFrame" id="mainFrame" />
</frameset>
<noframes><body></body></noframes>
    <div class="g-lwrap">
    	<c:if test=""></c:if>
        <div class="m-box container-fluid f-clearfix" id="masonry">
            <div class="m-catalog">
                <div class="catalog-title color0">
                    <h2><i class="iconfont">&#xe626;</i><a href="">电脑整机</a></h2></div>
                <!-- 一级 -->
                <ul class="catalog-menu">
                    <li>
                        <dl class="f-clearfix">
                            <dt class="f-fl"><a href="">一体机</a></dt>
                            <!-- 二级 -->
                            <dd class="f-fl"><a href="">三级类目</a>
                                <a href="">没有则不显示</a>
                                <a href="">三级类目</a>
                                <a href="">三级类目</a><a href="">没有则不显示</a>
                                <a href="">三级类目</a><a href="">三级类目</a><a href="">没有则不显示</a><a href="">三级类目</a><a href="">三级类目</a><a href="">没有则不显示</a><a href="">三级类目</a></dd>
                            <!-- 三级 -->
                        </dl>
                    </li>
                    <li>
                        <dl class="f-clearfix">
                            <dt class="f-fl"><a href="">一体机</a></dt>
                            <!-- 二级 -->
                            <dd class="f-fl"><a href="">三级类目</a>
                                <a href="">没有则不显示</a>
                                <a href="">三级类目</a>
                                <a href="">三级类目</a><a href="">没有则不显示</a>
                                <!-- 三级 -->
                        </dl>
                    </li>
                    <li>
                        <dl class="f-clearfix">
                            <dt class="f-fl"><a href="">一体机</a></dt>
                            <!-- 二级 -->
                            <dd class="f-fl"><a href="">三级类目</a>
                                <a href="">没有则不显示</a>
                                <a href="">三级类目</a>
                                <a href="">三级类目</a><a href="">没有则不显示</a>
                                <a href="">三级类目</a><a href="">三级类目</a><a href="">没有则不显示</a><a href="">三级类目</a><a href="">三级类目</a><a href="">没有则不显示</a><a href="">三级类目</a></dd>
                            <!-- 三级 -->
                        </dl>
                    </li>
                </ul>
            </div>
         </div>
    </div>
</html>
