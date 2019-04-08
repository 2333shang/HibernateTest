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
		$("[name='b']").click(function(){
			$("[name='b']").parent().parent().remove();
		return false;
		})
	})
</script>
<body>

	<table>
		<tr id="a">
			<td><a name="b" href="login.jsp">点击</a></td>
		</tr>
	</table>

</body>
</html>