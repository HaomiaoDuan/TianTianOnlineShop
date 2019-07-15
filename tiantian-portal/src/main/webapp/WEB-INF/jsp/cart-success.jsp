<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="format-detection" content="telephone=no">

<link href="/css/taotao.css" rel="stylesheet"/>
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
<title>商品已成功加入购物车</title>
</head>
<body>

    <jsp:include page="commons/header.jsp" />
	<!--main start-->
	<div class="cartMain">
		<div class="cartThead" style="margin: 50px 0px 50px 50px;">
			<h3 class="cartMy">您的商品已经成功加入购物车！</h3>
			<div class="cartJsuan">
				<input type="button" class="goshop" onclick="window.history.back();return false;" value="返回"/>
				<input type="button" class="jiesuan youxuan" onclick="location.href='http://localhost:8082/cart/cart.html'" value="去购物车结算"/>
			</div>
		</div>

	</div>
	<jsp:include page="commons/footer.jsp" />


</body>
</html>