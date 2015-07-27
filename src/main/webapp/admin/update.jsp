<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="security"  uri="/security" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>权限保护页面</title>
</head>
<body>
<h1>权限保护页面</h1>

<!-- 如果是注册用户登录，则显示如下内容 -->
<security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_GUEST, ROLE_USER">
    Username:<security:authentication property="principal.username"/><br/>
    Password:<security:authentication property="principal.password"/><br/>
    AcountNonExpired:<security:authentication
        property="principal.accountNonExpired"/><br/>
    AccountNonLocked:<security:authentication
        property="principal.accountNonLocked"/><br/>
    credentialsNonExpired:<security:authentication
        property="principal.credentialsNonExpired"/><br/>
    Enabled:<security:authentication property="principal.enabled"/><br/>
    Granted_Authorities:
    <s:iterator value="#users.getAuthoritiesString()">
        <s:property/>;
    </s:iterator>
    <p/>
</security:authorize>
</body>
</html>