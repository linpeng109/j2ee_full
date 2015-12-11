<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="struts" uri="struts-tags" %>
<%@taglib prefix="security" uri="security-tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>权限保护页面</title>
</head>
<body>
<h1>权限保护页面</h1>
<h2>当前用户权限信息：</h2>
<!-- 如果是注册用户登录，则显示如下内容 -->
<security:authorize access="hasRole('ROLE_ADMIN')">
    Username=<security:authentication property="principal.username"/><br/>
    Password=<security:authentication property="principal.password"/><br/>
    AcountNonExpired=<security:authentication property="principal.accountNonExpired"/><br/>
    AccountNonLocked=<security:authentication property="principal.accountNonLocked"/><br/>
    credentialsNonExpired=<security:authentication
        property="principal.credentialsNonExpired"/><br/>
    Enabled=<security:authentication property="principal.enabled"/><br/>
    Granted_Authorities:
    <security:authentication property="principal.authorities"/>
</security:authorize>
<p/>
<a href="../index.jsp">首页</a>
<a href="../logout">退出</a>
</body>
</html>