<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="struts-tags" %>
<%@taglib prefix="security" uri="security-tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>
        <s:text name="error.title"/>
    </title>
</head>

<body>
<h2>
    <!-- 取得本地化信息的第一种方法：直接使用text标签-->
    <s:label>
        <s:text name="error.title"/>
    </s:label>
</h2>

<!-- 取得本地化信息的第二种方法：使用getText函数 -->
<s:label value="%{getText('error.type')}"/>：
<br/>
<!-- 取得错误信息 -->
<s:property value="exception"/><p/>

<!-- 打印错误栈堆信息 -->
<s:label value="%{getText('error.detail')}"/>：<br>
<s:property value="%{exceptionStack}"/>

</body>
</html>
