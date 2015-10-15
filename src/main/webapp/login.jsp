<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="struts-tags" %>
<html>
	<head>
		<title>用户登录界面</title>
	</head>

	<body onload='document.f.username.focus();'>

		<h3>用户登录</h3>
		<s:set name="error">${param['error']==""}</s:set>

		<form name='f' action='/j2ee_full/login' method='POST'>
			<table>
				<tr><td>用户:</td><td><input type='text' name='username' value=''></td></tr>
				<tr><td>口令:</td><td><input type='password' name='password'/></td></tr>
				<tr><td colspan='2'><input name="submit" type="submit" value="登录"/></td></tr>
			</table>
		</form>

		<s:if test="#error">
			<p>错误道用户名或用户口令，请重新输入！</p>
		</s:if>

	</body>
</html>