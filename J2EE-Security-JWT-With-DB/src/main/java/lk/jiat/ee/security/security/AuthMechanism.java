package lk.jiat.ee.security.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
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
import lk.jiat.ee.security.util.JWTUtil;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AutoApplySession
@ApplicationScoped
public class AuthMechanism implements HttpAuthenticationMechanism {

    @Inject
    private AppIdentityStore identityStore;


    private static final Set<String> WHITELIST = Set.of(
            "/login",
            "/register",
            "/auth/login",
            "/auth/register",
            "/public"
    );


    private boolean isWhitelisted(String path) {
        return WHITELIST.stream().anyMatch(path::startsWith);
    }

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request,
                                                HttpServletResponse response,
                                                HttpMessageContext context) throws AuthenticationException {


       String authHeader = request.getHeader("Authorization");
       if (authHeader != null && authHeader.startsWith("Bearer ")) {
           try {
               String token = authHeader.substring(7);
               Claims claimsJws= JWTUtil.parseToken(token).getPayload();
               String username = claimsJws.getSubject();
               List roles =claimsJws.get("roles",List.class);

               CredentialValidationResult result = new CredentialValidationResult(username,new HashSet<>(roles));
               return context.notifyContainerAboutLogin(result);
           } catch (JwtException e) {
             return context.responseUnauthorized();
           }


       }


        AuthenticationParameters authParameters = context.getAuthParameters();
        System.out.println(authParameters.getCredential());

        if (authParameters.getCredential() != null) {
            CredentialValidationResult result = identityStore.validate(authParameters.getCredential());
            if (result.getStatus() == CredentialValidationResult.Status.VALID) {
                return context.notifyContainerAboutLogin(result);
            } else {
                return AuthenticationStatus.SEND_FAILURE;
            }
        }


        if (context.isProtected()) {
            try {
                response.sendRedirect(request.getContextPath()+"/login.jsp");
                return AuthenticationStatus.SEND_CONTINUE;
            } catch (IOException e) {
                throw new RuntimeException("Redirect to login failed", e);
            }
        }

        return context.doNothing();
    }
}
