package lk.jiat.ee.security.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import lk.jiat.ee.security.service.LoginService;

import java.util.Set;


@ApplicationScoped
public class AppIdentityStore implements IdentityStore {

    @Inject
    private LoginService loginService;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        System.out.println("credential "+credential);

        if(credential instanceof UsernamePasswordCredential){
            UsernamePasswordCredential upc = (UsernamePasswordCredential)credential;
            System.out.println("upc "+upc);
          boolean validate =  loginService.validateUser(upc.getCaller(), upc.getPasswordAsString());
          if(validate){
             Set<String> roles = loginService.getRoles(upc.getCaller());
             return new  CredentialValidationResult(upc.getCaller(),roles);

          }


        }
        return CredentialValidationResult.INVALID_RESULT;
    }
}
