<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>pager.jsp</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function _go(){
		var pc=$("#PAGENO").val();
		if(!/^[1-9]\d*$/.test(pc)){
			alert("请输入正确的页码！");
			return;
		}
		if(pc>"${pb.tp }"){
			alert("请输入正确的页码！");
			return;
		}
		location="${pb.url }pc="+pc;
	}
	</script>

  </head>
  
  <body>
    <div class="page pb15">
    	<span class="r inb_a page_b">
    		
    	<c:choose>
    		<c:when test="${pb.pc eq 1}">
    			<font size="2">首页</font>
    			<font size="2">上一页</font>
    		</c:when>
    		<c:otherwise>
    			<a href="${pb.url }pc=1"><font size="2">首页</font></a>
    			<a href="${pb.url }pc=${pb.pc-1 }"><font size="2">上一页</font></a>
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
    			<font size="2">...</font>
    		</c:if>
    		
    		<c:forEach begin="${beg }" end="${end }" var="i"  >
    			<c:choose>
    			<c:when test="${pb.pc eq i }">
    				<strong>${i }</strong>
    			</c:when>
    			<c:otherwise>
    				<a href="${pb.url }pc=${i }">${i }</a>
    			</c:otherwise>
    			</c:choose>
    		</c:forEach>
    		
    		<c:if test="${end < pb.tp }">
    			<font size="2">...</font>
    		</c:if>
    		
    		<c:choose>
    			<c:when test="${pb.pc eq pb.tp }">
    				<font size="2">下一页</font>
    				<font size="2">尾页</font>
    			</c:when>
    			<c:otherwise>
    				<a href="${pb.url }pc=${pb.pc+1 }"><font size="2">下一页</font></a>
    				<a href="${pb.url }pc=${pb.tp }"><font size="2">尾页</font></a>
    			</c:otherwise>
    		</c:choose>
    		
    		共<var>${pb.tp }</var>页 到第<input type="text" id="PAGENO" size="3" value="${pb.pc }"/>页
    		<input type="button" onclick="javascript:_go() " value="确定" class="hand btn60x20" id="skip"/>
    	</span>
    </div>
  </body>
</html>
