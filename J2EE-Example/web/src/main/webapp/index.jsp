<%@ page import="javax.naming.InitialContext" %>
<%@ page import="lk.jiat.ee.core.service.ProductService" %>
<%@ page import="java.util.List" %>
<%@ page import="lk.jiat.ee.core.model.Product" %>
<%@ page import="javax.naming.NamingException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ravis
  Date: 7/5/2025
  Time: 8:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Home Page !</h1>
<c:if test="${empty pageContext.request.userPrincipal}">
    <a href="${pageContext.request.contextPath}/register.jsp">Register</a>
    <a href="${pageContext.request.contextPath}/login.jsp">Login</a>

</c:if>

<c:if test="${not empty pageContext.request.userPrincipal}">
    <a href="${pageContext.request.contextPath}/logout">Log Out</a>

</c:if>

<%
    try {
        InitialContext initialContext = new InitialContext();
        ProductService productService = (ProductService) initialContext.lookup("lk.jiat.ee.core.service.ProductService");
        List<Product> products = productService.getAllProduct();
        pageContext.setAttribute("products",products);
    } catch (NamingException e) {
        throw new RuntimeException(e);
    }

%>

<table>
    <tr>
        <th>Product</th>
        <th>Category</th>
        <th>Description</th>
        <th>Price</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.name}</td>
            <td>${product.category}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
