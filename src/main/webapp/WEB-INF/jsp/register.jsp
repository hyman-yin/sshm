<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Register...</h1>
	<form action="register222.do" method="post">
		username: <input type="text" name="username" required="required" value="${requestScope.username_error==null?requestScope.info.username:'' }">&nbsp;<font color="red">${requestScope.username_error }</font><br/>
		password:<input type="password" name="password" value="${requestScope.password_error==null?requestScope.info.password:'' }" />&nbsp;<font color="red">${requestScope.password_error }</font><br/>
		repeat password: <input type="password" name="password2" value="${requestScope.password2_error==null?requestScope.info.password2:'' }" />&nbsp;<font color="red">${requestScope.password2_error }</font><br/>
		tel: <input type="text" name="tel" value="${requestScope.tel_error==null?requestScope.info.tel:'' }"/>&nbsp;<font color="red">${requestScope.tel_error }</font><br/>
		email: <input type="text" name="email" value="${requestScope.email_error==null?requestScope.info.email:'' }" />&nbsp;<font color="red">${requestScope.email_error }</font><br/>
		age: <input type="text" name="age" value="${requestScope.age_error==null?requestScope.info.age:'' }" />&nbsp;<font color="red">${requestScope.age_error }</font><br/>
		
		<input type="submit" value="submit" />
		<input type="reset" value="reset" />
	</form>
	
	<hr>
	<%-- <c:if test="${not empty requestScope.msg }"> --%>
		${requestScope.msg }
	<!-- </c:if> -->
	
</body>
</html>