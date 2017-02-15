<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>pager.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/pager/pager.css'/>" ></link>
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>" ></script>
	<script type="text/javascript">
	function _go(){
		var pc=$("#pageCode").val();
		if(!/^[1-9]\d*$/.test(pc)){
			alert("请输入正确的页码！");
			return;
		}
		if(pc>"${pb.tp }"){
			alert("请输入正确的页码！");
			return;
		}
		location="${pb.url }&pc="+pc;
	}
	</script>

  </head>
  
  <body>
    <div id="divMain">
    	<div id="divContent">
    		
    	<c:choose>
    		<c:when test="${pb.pc eq 1}">
    			<span class="disabledClass">上一页</span>
    		</c:when>
    		<c:otherwise>
    			<a class="aClass bold" href="${pb.url }&pc=${pb.pc-1 }">上一页</a>
    		</c:otherwise>
    	</c:choose>
    		
    		<!-- 
    		1、tp<=6,beg=1,end=tp
    		2、否则，beg=pc-2,end=pc+3
    			if beg<1,beg=1,end=6;
    			if end>6,beg=pc-5,end=tp;
    		3、if end<tp,显示省略号
    		 -->
    		<c:choose>
    			<c:when test="${pb.tp <= 6 }">
    				<c:set var="beg" value="1"></c:set>
    				<c:set var="end" value="${pb.tp }"></c:set>
    			</c:when>
    			<c:otherwise>
    				<c:set var="beg" value="${pb.pc-2 }"></c:set>
    				<c:set var="end" value="${pb.pc+3 }"></c:set>
    				<c:if test="${beg < 1 }">
    					<c:set var="beg" value="1"></c:set>
    					<c:set var="end" value="6"></c:set>
    				</c:if>
    				<c:if test="${end > pb.tp }">
    					<c:set var="beg" value="${pb.tp-5 }"></c:set>
    					<c:set var="end" value="${pb.tp }"></c:set>
    				</c:if>
    			</c:otherwise>
    		</c:choose>
    		
    		<c:if test="${beg > 1 }">
    			<span id="spoint">...</span>
    		</c:if>
    		
    		<c:forEach begin="${beg }" end="${end }" var="i"  >
    			<c:choose>
    			<c:when test="${pb.pc eq i }">
    				<span class="SelectClass">${i }</span>
    			</c:when>
    			<c:otherwise>
    				<a class="aClass" href="${pb.url }&pc=${i }">${i }</a>
    			</c:otherwise>
    			</c:choose>
    		</c:forEach>
    		
    		<c:if test="${end < pb.tp }">
    			<span id="spoint">...</span>
    		</c:if>
    		
    		<c:choose>
    			<c:when test="${pb.pc eq pb.tp }">
    				<span class="disabledClass">下一页</span>
    			</c:when>
    			<c:otherwise>
    				<a class="aClass bold" href="${pb.url }&pc=${pb.pc+1 }">下一页</a>
    			</c:otherwise>
    		</c:choose>
    		
    		<span>共${pb.tp }页</span>
    		<span>到</span>
    		<input id="pageCode" name="pageCode" value="${pb.pc }"/>
    		<span>页</span>
    		<a href="javascript:_go()" id="submitBtn">确认</a>
    	</div>
    </div>
  </body>
</html>
