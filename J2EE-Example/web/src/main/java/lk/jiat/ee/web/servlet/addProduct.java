package lk.jiat.ee.web.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.core.model.Product;
import lk.jiat.ee.core.service.ProductService;

import java.io.IOException;

@ServletSecurity(@HttpConstraint(rolesAllowed ={"ADMIN","SUPER_ADMIN"}))
@WebServlet("/admin/add_product")
public class addProduct extends HttpServlet {

    @EJB
    private ProductService productService;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDesc");
        String productCategory = request.getParameter("productCategory");
        String productPrice = request.getParameter("productPrice");
        String productQty = request.getParameter("productQty");

        Product product = new Product(productName,productDescription,Double.parseDouble(productPrice),Double.parseDouble(productQty),productCategory);
        productService.addProduct(product);

        response.sendRedirect(request.getContextPath()+"/index.jsp");

    }
}
