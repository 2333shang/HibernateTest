<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.js"></script>
<script type="text/javascript">
	$(function(){
		$("#um").change(function(){
			var val=$(":input[name='username']").val();
			val=$.trim(val);
			if(val!=""){
				
				var url="${pageContext.request.contextPath}/ajaxRegister.do";
				var args={"username":val,date:new Date()};
				$("#message").load(url,args);
			}
		});
	})
</script>
<body>
<center>
<form action="${pageContext.request.contextPath}/register.do" method="post">
	<table cellpadding="10" cellspacing="0" border="1">
		<tr>
			<td>用户名:</td>
			<td id="um">
				<input type="text" name="username"/><br/>
				<div id="message"></div>
			</td>
		</tr>
		<tr>
			<td>密码:</td>
			<td><input type="password" name="password"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<center>
					<input type="submit" value="注册"/>
					<input type="reset" value="重置"/>
				</center>
			</td>
		</tr>
	</table>
</form>
</center>
</body>
</html>