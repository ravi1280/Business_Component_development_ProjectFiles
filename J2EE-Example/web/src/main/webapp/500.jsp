<%--
  Created by IntelliJ IDEA.
  User: ravis
  Date: 7/7/2025
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>500 Error</h1>
${requestScope['jakarta.servlet.error.status_code']}
${requestScope['jakarta.servlet.error.message']}

</body>
</html>
