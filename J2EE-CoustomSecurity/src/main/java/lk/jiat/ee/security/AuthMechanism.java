package lk.jiat.ee.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.AutoApplySession;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@AutoApplySession
@ApplicationScoped
public class AuthMechanism implements HttpAuthenticationMechanism {

    @Inject
    private IdentityStore identityStore;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse,
                                                HttpMessageContext httpMessageContext) throws AuthenticationException {
        System.out.println("validateRequest");

        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");

        if (username != null && password != null) {
            //Authenticate Using Username and password

            CredentialValidationResult result = identityStore.validate(new UsernamePasswordCredential(username, password));
            System.out.println(result.getStatus());
            System.out.println(" Caller Group :  " + result.getCallerGroups());

            if (result.getStatus() == CredentialValidationResult.Status.VALID) {
                return httpMessageContext.notifyContainerAboutLogin(result);
            } else {

                try {
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return AuthenticationStatus.SEND_FAILURE;
            }
        }


        if (httpMessageContext.isProtected()) {
            try {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
                return AuthenticationStatus.SEND_CONTINUE;
            } catch (IOException e) {
                throw new RuntimeException("Redirect to login Failed" + e);
            }
        }
        return httpMessageContext.doNothing();
    }
}
