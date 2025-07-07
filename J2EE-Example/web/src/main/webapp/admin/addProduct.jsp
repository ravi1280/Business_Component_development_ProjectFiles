<%--
  Created by IntelliJ IDEA.
  User: ravis
  Date: 7/6/2025
  Time: 10:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
</head>
<body>

<h1>Add Product</h1>
<form method="POST" action="${pageContext.request.contextPath}/admin/add_product">
    <table>
        <tr>
            <th>Product Name</th>
            <td><input type="text" name="productName"></td>
        </tr>
        <tr>
            <th>Product Description</th>
            <td><input type="text" name="productDesc"></td>
        </tr>
        <tr>
            <th>Product Category</th>
            <td><input type="text" name="productCategory"></td>
        </tr>
        <tr>
            <th>Product Price</th>
            <td><input type="text" name="productPrice"></td>
        </tr>
        <tr>
            <th>Product Qty</th>
            <td><input type="text" name="productQty"></td>
        </tr>
        <tr>

            <td><input type="submit" value="Add  Product"></td>
        </tr>
    </table>

</form>

</body>
</html>
