<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <!-- 2010-03-30修改获得用户权限字符串的方法 -->
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title>用户登录</title>
</head>
<body>
<br/>
当前登录用户信息:<br/>

<!-- 如果是匿名用户登录，则显示以下内容 -->
<security:authorize access="hasRole('ROLE_ADMIN')">
    匿名用户 <p/>
</security:authorize>

<!-- 显示错误信息 -->
<s:if test="#parameters.login_error!=null">
    <s:div cssStyle="color:red">
        <s:property value="#session.SPRING_SECURITY_LAST_EXCEPTION"/>
    </s:div><p/>
</s:if>

<!-- 只有具有相应权限的用户可以显示 -->
<form action="j_spring_security_check" method="post">
    用户 ：<input type="text" name="j_username"/><br>
    口令 ：<input type="password" name="j_password"/><br>
    <input type="submit" value="登录"/>
</form>
<p/>

<!-- 退出登录 -->
<s:a href="j_spring_security_logout">退出登录</s:a>
<p/>

</body>
</html>