<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="struts-tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Websocket消息传送——服务器端测试页</title>
</head>
<body>
<h1>Websocket消息传送——服务器端测试页</h1>
<form name='sendMessageToClient' action='/j2ee_full/action/websocketAction!sendMessageToClient.action' method='POST'>
    <table>
        <tr>
            <td>SessionId:</td>
            <td><input type='text' name='sessionId' value=''></td>
        </tr>
        <tr>
            <td>MessageText:</td>
            <td><input type='text' name='messageText'/></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="发送"/></td>
        </tr>
    </table>
</form>
<p></p>

<form name='sendMessageToAll' action='/j2ee_full/action/websocketAction!sendMessageToAll.action' method='POST'>
    <table>
        <tr>
            <td>MessageText:</td>
            <td><input type='text' name='messageText'/></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="发送"/></td>
        </tr>
    </table>
</form>
</body>
</html>