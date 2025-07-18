package lk.jiat.ee.web.servlet;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;

import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.ee.core.exception.LoginFailedException;
import lk.jiat.ee.core.service.UserService;
import lk.jiat.ee.core.util.Encryption;

import java.io.IOException;


@WebServlet("/login")
public class Login extends HttpServlet {


    @Inject
    private SecurityContext securityContext;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("Email: " + email+" Password: " + password);

        String encryptPassword = Encryption.encrypt(password);
        System.out.println("Encrypted password: " + encryptPassword);

        AuthenticationParameters parameters = AuthenticationParameters.withParams()
                .credential(new UsernamePasswordCredential(email, Encryption.encrypt(password)));

        System.out.println("parameters: " + parameters);

        AuthenticationStatus status = securityContext.authenticate(request, response, parameters);
        System.out.println("Authentication status: " + status);

        if (status == AuthenticationStatus.SUCCESS) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");

        } else {
            throw new LoginFailedException(" Invalid email or password");

//            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

    }
}
