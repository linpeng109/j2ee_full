<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="struts-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>用户列表</title>
    </head>
    <body>
        <h1>用户列表</h1>
        <table border="1">
            <s:iterator value="list">
                <tr>
                    <td>用户id：<s:property value="userId"/></td>
                    <td>用户名：<s:property value="userName"/></td>
                    <td>创建时间：<s:property value="createDate"/></td>
                    <td>用户权限：
                        <s:iterator value="userBase_authoritys">
                            <s:property value="authority.authorityString"/>;
                        </s:iterator>
                    </td>
                </tr>
            </s:iterator>
        </table>
    </body>
</html>