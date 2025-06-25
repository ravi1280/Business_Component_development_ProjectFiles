<%--
  Created by IntelliJ IDEA.
  User: ravis
  Date: 6/24/2025
  Time: 1:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%--<%--%>
<%--    if(session.getAttribute("user")==null){--%>
<%--        response.sendRedirect("login.jsp");--%>
<%--    }--%>
<%--%>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JTA Bank | Account </title>
</head>
<body>
<h1>Hello Account ${sessionScope.user}</h1>
<form method="POST" action="tranfer"></form>
<table>
    <tr>
        <th>Source Account Number</th>
        <td><input type="text" name="sourceAccountNumber"></td>
    </tr>
    <tr>
        <th>Destination Account Number</th>
        <td><input type="text" name="destinationAccountNumber"></td>
    </tr>
    <tr>
        <th>Amount</th>
        <td><input type="text" name="amount"></td>
    </tr>

    <tr>
        <th></th>
        <td><input type="submit" name="Transfer"></td>
    </tr>
</table>

</body>
</html>
