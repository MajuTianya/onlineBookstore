<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>pay</title>
    
	<meta http-equiv="pragma" content="no-cache"></meta>
	<meta http-equiv="cache-control" content="no-cache"></meta>
	<meta http-equiv="expires" content="0"></meta>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"></meta>
	<meta http-equiv="description" content="This is my page"></meta>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/order/pay.css'/>" ></link>
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript">
$(function() {
	$("img").click(function() {
		$("#" + $(this).attr("name")).attr("checked", true);
	});
});
</script>

  </head>
  
  <body>
    <div id="mainDiv">
    <div id="contentDiv">
  		<span id="priceSpan">支付金额：</span><span id="price_t">&yen;${order.total }</span><span id="oidSpan">编号：${order.oid }</span>
  	</div>
  	<div id="bankDiv">
  		<div id="textDiv">选择网上银行</div>
  		<form action="<c:url value='/OrderServlet'/>" method="post" id="form1" target="_top">
  		<input type="hidden" name="method" value="payment"/>
  		<input type="hidden" name="oid" value="${order.oid }"/>
  		<div style="margin-left:20px; ">
  			<div style="margin-bottom:20px; ">
  				<input id="ICBC-NET-B2C" type="radio" name="yh" value="ICBC-NET-B2C" checked="checked"/>
  				<img align="middle" name="ICBC-NET-B2C" src="<c:url value='/bank_img/icbc.bmp'/>"/>
  				
  				<input id="CMBCHINA-NET-B2C" type="radio" name="yh" value="CMBCHINA-NET-B2C"/>
  				<img align="middle" name="CMBCHINA-NET-B2C " src="<c:url value='/bank_img/cmb.bmp'/>"/>
  				
  				<input id="ABC-NET-B2C" type="radio" name="yh" value="ABC-NET-B2C"/>
  				<img align="middle" name="ABC-NET-B2C" src="<c:url value='/bank_img/abc.bmp'/>"/>
  				
  				<input id="CCB-NET-B2C" type="radio" name="yh" value="CCB-NET-B2C"/>
  				<img align="middle" name="CCB-NET-B2C" src="<c:url value='/bank_img/ccb.bmp'/>"/>
  			</div>
  			<div style="margin-bottom:20px; ">
  				<input id="BCCB-NET-B2C" type="radio" name="yh" value="BCCB-NET-B2C"/>
  				<img align="middle" name="BCCB-NET-B2C" src="<c:url value='/bank_img/bj.bmp'/>"/>
  				
  				<input id="BOCO-NET-B2C" type="radio" name="yh" value="BOCO-NET-B2C"/>
  				<img align="middle" name="BOCO-NET-B2C" src="<c:url value='/bank_img/bcc.bmp'/>"/>
  				
  				<input id="CIB-NET-B2C" type="radio" name="yh" value="CIB-NET-B2C"/>
  				<img align="middle" name="CIB-NET-B2C" src="<c:url value='/bank_img/cib.bmp'/>"/>
  				
  				<input id="NJCB-NET-B2C" type="radio" name="yh" value="NJCB-NET-B2C"/>
  				<img align="middle" name="NJCB-NET-B2C" src="<c:url value='/bank_img/nanjing.bmp'/>"/>
  			</div>
  			<div style="margin-bottom:20px; ">
  				<input id="CMBC-NET-B2C" type="radio" name="yh" value="CMBC-NET-B2C"/>
  				<img align="middle" name="CMBC-NET-B2C" src="<c:url value='/bank_img/cmbc.bmp'/>"/>
  				
  				<input id="CEB-NET-B2C" type="radio" name="yh" value="CEB-NET-B2C"/>
  				<img align="middle" name="CEB-NET-B2C" src="<c:url value='/bank_img/guangda.bmp'/>"/>
  				
  				<input id="BOC-NET-B2C" type="radio" name="yh" value="BOC-NET-B2C"/>
  				<img align="middle" name="BOC-NET-B2C" src="<c:url value='/bank_img/bc.bmp'/>"/>
  				
  				<input id="PINGANBANK-NET" type="radio" name="yh" value="PINGANBANK-NET"/>
  				<img align="middle" name="PINGANBANK-NET" src="<c:url value='/bank_img/pingan.bmp'/>"/>
  			</div>
  			<div style="margin-bottom:20px; ">
  				<input id="CBHB-NET-B2C" type="radio" name="yh" value="CBHB-NET-B2C" />
  				<img align="middle" name="CBHB-NET-B2C" src="<c:url value='/bank_img/bh.bmp'/>"/>
  				
  				<input id="HKBEA-NET-B2C" type="radio" name="yh" value="HKBEA-NET-B2C"/>
  				<img align="middle" name="HKBEA-NET-B2C" src="<c:url value='/bank_img/dy.bmp'/>"/>
  				
  				<input id="NBCB-NET-B2C" type="radio" name="yh" value="NBCB-NET-B2C"/>
  				<img align="middle" name="NBCB-NET-B2C" src="<c:url value='/bank_img/ningbo.bmp'/>"/>
  				
  				<input id="ECITIC-NET-B2C" type="radio" name="yh" value="ECITIC-NET-B2C"/>
  				<img align="middle" name="ECITIC-NET-B2C" src="<c:url value='/bank_img/zx.bmp'/>"/>
  			</div>
  			<div style="margin-bottom:20px; ">
  				<input id="SDB-NET-B2C" type="radio" name="yh" value="SDB-NET-B2C"/>
  				<img align="middle" name="SDB-NET-B2C" src="<c:url value='/bank_img/sfz.bmp'/>"/>
  				
  				<input id="GDB-NET-B2C" type="radio" name="yh" value="GDB-NET-B2C"/>
  				<img align="middle" name="GDB-NET-B2C" src="<c:url value='/bank_img/gf.bmp'/>"/>
  				
  				<input id="SHB-NET-B2C" type="radio" name="yh" value="SHB-NET-B2C"/>
  				<img align="middle" name="SHB-NET-B2C" src="<c:url value='/bank_img/sh.bmp'/>"/>
  				
  				<input id="SPDB-NET-B2C" type="radio" name="yh" value="SPDB-NET-B2C"/>
  				<img align="middle" name="SPDB-NET-B2C" src="<c:url value='/bank_img/shpd.bmp'/>"/>
  			</div>
  			<div style="margin-bottom:20px; ">
  				<input id="POST-NET-B2C" type="radio" name="yh" value="POST-NET-B2C"/>
  				<img align="middle" name="POST-NET-B2C" src="<c:url value='/bank_img/post.bmp'/>"/>
  				
  				<input id="BJRCB-NET-B2C" type="radio" name="yh" value="BJRCB-NET-B2C"/>
  				<img align="middle" name="BJRCB-NET-B2C" src="<c:url value='/bank_img/beijingnongshang.bmp'/>"/>
  				
  				<input id="HXB-NET-B2C" type="radio" name="yh" value="HXB-NET-B2C"/>
  				<img align="middle" name="HXB-NET-B2C" src="<c:url value='/bank_img/hx.bmp'/>"/>
  				
  				<input id="CZ-NET-B2C" type="radio" name="yh" value="CZ-NET-B2C"/>
  				<img align="middle" name="CZ-NET-B2C" src="<c:url value='/bank_img/zheshang.bmp'/>"/>
  			</div>
  		</div>
  		</form>
  		<div style="margin-left:40px; ">
  			<a href="javascript:$('#form1').submit();" id="linkNext">下一步</a>
  		</div>
  	</div>
    </div>
  </body>
</html>
