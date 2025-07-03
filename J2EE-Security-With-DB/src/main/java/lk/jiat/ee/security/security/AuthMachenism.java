package lk.jiat.ee.security.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.AutoApplySession;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Set;


@AutoApplySession
@ApplicationScoped
public class AuthMachenism implements HttpAuthenticationMechanism {
    @Inject
    private IdentityStore identityStore;

    private static final Set<String> WHITELIST =
            Set.of("/login",
                    "/register",
                    "/auth/login",
                    "/auth/register",
                    "/public",
                    "/index.jsp");


    private boolean isWhitelisted(String path) {
        return WHITELIST.stream().anyMatch(path::startsWith);
    }

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest Request,
                                                HttpServletResponse Response,
                                                HttpMessageContext Context) throws AuthenticationException {

        //with Context Path
        String path = Request.getRequestURI();

        //without Context Path
        String path2 = Request.getServletPath();
        System.out.println(path2);

        if (isWhitelisted(path2)) {
            return Context.doNothing(); //No need Authenticate
        }
        System.out.println("Auth Parameters" +Context.getAuthParameters());
        return Context.responseUnauthorized();
    }
}
