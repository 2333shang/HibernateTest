<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		$(":input[name='username']").blur(function(){
			var val=$(this).val();
			val=$.trim(val);
			if(val==""){
				alert("请输入用户名！");
			}
		})
		$(":input[name='password']").blur(function(){
			var val=$(this).val();
			val=$.trim(val);
			if(val==""){
				alert("请输入密码！");
			}
		})
	})
</script>
<center>
<form action="${pageContext.request.contextPath}/login.do" method="post">
	<table cellpadding="10" cellspacing="0" border="1">
		<tr>
			<td>用户名:</td>
			<td>
				<input type="text" name="username" value="${userMessage2}"/>
				<font color="red">${userMessage1}</font>
			</td>
		</tr>
		<tr>
			<td>密码:</td>
			<td>
				<input type="password" name="password"/>
				<font color="red">${pasdMessage}</font>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<center>
					<input type="submit" value="登录"/>
					<input type="reset" value="重置"/>
					<a href="${pageContext.request.contextPath}/register.jsp">注册</a>
				</center>
			</td>
		</tr>
	</table>
</form>
</center>
</body>
</html>