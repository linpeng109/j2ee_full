<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Basic Struts 2 Application - Welcome</title>
    </head>
    <body>
        <h1>欢迎使用Struts2!</h1>
        <p>Your principal is <%=request.getUserPrincipal() %></p>
        <p><a href="<s:url action='action/userBaseAction.action'/>">jsp页面显示数据</a></p>
        <p><a href="<s:url action='json/userBaseAction.action'/>">json格式数据</a></p>
        <p><a href="admin/admin.jsp">保护数据</a></p>
        <p><a href="jsonp.html">跨域获取JSONP数据</a></p>
    </body>
</html>