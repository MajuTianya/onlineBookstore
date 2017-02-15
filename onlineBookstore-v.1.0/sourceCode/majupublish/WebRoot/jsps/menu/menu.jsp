<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>menu.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/menu/menu.css'/>"></link>
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsps/menu/menu.js'/>"></script>

  </head>
  <body>
  <div id="menu">
  	<span id="oneUl">
  		<c:forEach items="${parents }" var="parent">
    			<span class="oneLi"><a href="#">${parent.cname }</a>
    			<c:choose>
    				<c:when test="${empty parent.children }">
    				</c:when>
    				<c:otherwise>
    					<span class="twoUl">
    						<c:forEach items="${parent.children }" var="child">
    							<span class="twoLi"><a href="<c:url value='BookServlet?method=findByCategory&cid=${child.cid }'/>" target="bodyIframe" >${child.cname }</a></span>
    						</c:forEach>
    					</span>
    				</c:otherwise>
    			</c:choose>
    			</span>
    	</c:forEach>
    </span>
  </div>
  </body>
</html>
