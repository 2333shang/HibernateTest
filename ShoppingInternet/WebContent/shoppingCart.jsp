<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.js"></script>
<script type="text/javascript">
	$(function(){
		$("[name='pay']").click(function(){
			var a=confirm("123");
			if(a){
				if($(this).parent().parent().eq(0).children().children().attr("checked")){
					window.location.href=this.href;
				}else{
					alert("请选中该商品！");
				}
			}else{
				
			}
			return false;
		})
		
		$("[name='payAll']").click(function(){
				var checkbox=$("[name='sel']");
				var goodsName=new Array();
				var a=0;
				for(var i=0;i<checkbox.length;i++){
					if ($("[name='sel']").eq(i).attr("checked")) {
						goodsName[a]=$("#shoppingCart").children().children().eq(i+1).children().eq(1).html();
						a++;
		             }
				}
				window.location.href=this.href+"?goodsNameArr="+goodsName;
			return false;
		})
		$("[name='del']").click(function(){
			if($(this).parent().parent().eq(0).children().children().attr("checked")){
				var index=$(this).parent().parent().index();
				var url=this.href;
				var args={"time":new Date()};
				$.getJSON(url,args,function(data){
					var money=$("#shoppingCart").children().children().eq(index).children().eq(2).html();
					$("#shoppingCart").children().children().eq(index).remove();
					$("#mes").text(data.goodsNameMes);
					$("#totalprice").text($("#totalprice").html()-money);
				})
			}else{
				alert("请选中该商品！");
			}
			return false;
		})
		$("[name='sel']").change(function(){
			var totalmoney=0;
			var checkbox=$("[name='sel']");
			for(var i=0;i<checkbox.length;i++){
				if ($("[name='sel']").eq(i).attr("checked")) {
					var money= $("[name='sel']").eq(i).parent().parent().children().eq(2).html();
					var count= $("[name='sel']").eq(i).parent().parent().children().eq(3).html();
	                totalmoney+=money*count;
	             }
			}
	        $("#totalprice").text(totalmoney);
	        totalmoney=0;
		})
	})
</script>
<center>
	<center><font size="8">${username}的购物车</font><font size="4" id="mes"></font></center><a href="${pageContext.request.contextPath}/shopping.jsp">返回</a>
	<table cellpadding="10" cellspacing="0" border="1" id="shoppingCart">
		<tr id="shoppingCart1">
			<td>选择</td>
			<td>商品名称:</td>
			<td>商品价格</td>
			<td>商品数量</td>
			<td>支付</td>
			<td>删除</td>
		</tr>
		<c:if test="${fn:length(shoppingCart.goods)!=0}">
			<c:forEach items="${shoppingCart.goods}" var="good">
				<tr>
					<td><input name="sel" type="checkbox"/></td>
					<td>${good.goodsName}</td>
					<td>${good.price}</td>
					<td>${good.number}</td>
					<td><a name="pay" href="pay.do?goodsName=${good.goodsName}">支付</a></td>
					<td><a name="del" href="del.do?goodsName=${good.goodsName}">删除</a></td>
				</tr>
			</c:forEach>
				<tr>
					<td colspan="4">总价格:<font id="totalprice" color="red"">0</font></td>
					<td><a name="payAll" href="${pageContext.request.contextPath}/payAll.do">付款已选中</a></td>
					<td><a name="delAll" href="${pageContext.request.contextPath}/delAll.do">清空购物车</a></td>
				</tr>
		</c:if>
		<c:if test="${fn:length(shoppingCart.goods)==0}">
			<tr>
				<td colspan="5">您的购物车空空如也，快去<a href="${pageContext.request.contextPath}/shopping.jsp">购买商品吧！</a></td>
			</tr>
		</c:if>
	</table>
</center>
</body>
</html>