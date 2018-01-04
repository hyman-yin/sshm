<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
	<h2>Login</h2>
	<form action="login.action" method="post">
		<tabel>
			<tr>
				<td>username: </td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>password: </td>
				<td><input type="password" name="password" /> </td>
			</tr>
		</tabel>
		<input type="submit" value="登录" />
		<input type="reset" value="reset" />
	</form>
</body>
</html>
