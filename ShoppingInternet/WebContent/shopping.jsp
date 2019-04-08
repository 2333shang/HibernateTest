<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="page" uri="/page"%> 
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
		$("#mes").hide();
		$("[name='g']").click(function(){
			var username='<%=session.getAttribute("username")%>'; 
			if(username!="null"){
				var url=this.href;
				var args={"time":new Date()};
				$.getJSON(url,args,function(data){
					$("#mes").show();
					$("#goodsName").text(data.goodsName);
				})
			}else{
				window.location.href="login.jsp"; 
			}
				return false;
		})
	})
</script>
<center>
	欢迎你,${username}
		<c:if test="${username!=null}">
			<a href="${pageContext.request.contextPath}/logout.do">注销</a>
		</c:if>
		<c:if test="${username==null}">
			<a href="${pageContext.request.contextPath}/login.jsp">登录</a>
		</c:if>
	&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/shoppingCart.jsp">购物车</a>
	<form action="${pageContext.request.contextPath}/selectGoodsByName.do" method="post">
		搜索<input type="text" name="selectName"/>
		<input type="submit" value="查询"/>
		<input type="submit" value="显示所有商品" formaction="${pageContext.request.contextPath}/selectGoods.do"/>
		<c:if test="${goods!=null}">
			<table cellpadding="10" cellspacing="0" border="1">
				<tr>
					<td>商品名称</td>
					<td>商品价格</td>
					<td>加进购物车</td>
				</tr>
				<c:forEach items="${goods}" var="g">
					<tr>
						<td>${g.goodsName}</td>
						<td>${g.price}</td>
						<td><a name="g" href="addToShoppingCart.do?goodsName=${g.goodsName}">加进购物车</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if><br>
		<c:if test="${type==2}">
			<c:choose>
				<c:when test="${page>1}"><page:fy link="${pageContext.request.contextPath}/selectGoods.do?page=${page-1}">上一页</page:fy></c:when>
				<c:otherwise><page:fy link="${pageContext.request.contextPath}/selectGoods.do?page=${page}">上一页</page:fy></c:otherwise>	
			</c:choose>
		</c:if>
		<c:if test="${type==1}">
			<c:choose>
				<c:when test="${page>1}"><page:fy link="${pageContext.request.contextPath}/selectGoods.do?page=${page-1}">上一页</page:fy></c:when>
				<c:otherwise><page:fy link="${pageContext.request.contextPath}/selectGoods.do?page=${page}">上一页</page:fy></c:otherwise>	
			</c:choose>
		</c:if>
		<c:if test="${goodsLength/5==0}">
			<c:set value="${goodsLength/5}" var="gl"/>
		</c:if>
		<c:if test="${goodsLength/5!=0}">
			<c:set value="${goodsLength/5+1}" var="gl"/>
		</c:if>
		<c:forEach begin="1" end="${gl}" var="g">
			<c:if test="${type==2}">
				<page:fy link="${pageContext.request.contextPath}/selectGoods.do?page=${g}">${g}</page:fy>&nbsp;
			</c:if>
			<c:if test="${type==1}">
				<page:fy link="${pageContext.request.contextPath}/selectGoodsByName.do?page=${g}">${g}</page:fy>&nbsp;
			</c:if>
		</c:forEach>
		<c:if test="${type==2}">
			<c:choose>
				<c:when test="${page<4}"><page:fy link="${pageContext.request.contextPath}/selectGoods.do?page=${page+1}">下一页</page:fy></c:when>
				<c:otherwise><page:fy link="${pageContext.request.contextPath}/selectGoods.do?page=${page}">下一页</page:fy></c:otherwise>	
			</c:choose>
		</c:if>
		<c:if test="${type==1}">
			<c:choose>
				<c:when test="${page<4}"><page:fy link="${pageContext.request.contextPath}/selectGoods.do?page=${page+1}">下一页</page:fy></c:when>
				<c:otherwise><page:fy link="${pageContext.request.contextPath}/selectGoods.do?page=${page}">下一页</page:fy></c:otherwise>
			</c:choose>	
		</c:if>
	</form>
	<div id="mes">您已将<span id="goodsName"></span>加入购物车</div>
</center>
</body>
</html>