<%@ page import="javax.naming.InitialContext" %>
<%@ page import="lk.jiat.ee.core.service.ProductService" %>
<%@ page import="lk.jiat.ee.core.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.naming.NamingException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ravis
  Date: 7/6/2025
  Time: 10:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<h1>Admin DashBoard</h1>
<%--<a href="${pageContext.request.contextPath+"/admin/addProduct.jsp"}">App product</a>--%>
<a href="${pageContext.request.contextPath}/admin/addProduct.jsp">App product</a>
<h1>All Product</h1>

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
        <th>Action</th>

    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.name}</td>
            <td>${product.category}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/delete_product?pid=${product.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
