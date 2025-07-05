package lk.jiat.ee.security.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.authentication.mechanism.http.AutoApplySession;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

//UsernamePasswordCredential fake = new UsernamePasswordCredential("admin", "123");
//CredentialValidationResult result = identityStore.validate(fake);

@AutoApplySession
@ApplicationScoped
public class AuthMechanism implements HttpAuthenticationMechanism {
    @Inject
    private IdentityStore identityStore;


    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest Request,
                                                HttpServletResponse Response,
                                                HttpMessageContext context) throws AuthenticationException {


        AuthenticationParameters parameters = context.getAuthParameters();
        System.out.println("getAuthParams cc ::"+parameters.getCredential());

        if(parameters.getCredential() !=null){
            CredentialValidationResult result = identityStore.validate(parameters.getCredential());
            System.out.println(result);
           if(result.getStatus() == CredentialValidationResult.Status.VALID){
               return context.notifyContainerAboutLogin(result);
           }else {
               return AuthenticationStatus.SEND_FAILURE;
           }

        }

        if(context.isProtected()){
            try {
                Response.sendRedirect(Request.getContextPath()+"/login.jsp");
                return AuthenticationStatus.SEND_CONTINUE;
            } catch (IOException e) {
                throw new RuntimeException("Error sending redirect to login page", e);
            }
        }

        System.out.println("Auth Parameters" +parameters);
        return context.responseUnauthorized();
    }
}
