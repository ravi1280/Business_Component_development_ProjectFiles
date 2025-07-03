package lk.jiat.ee.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.AutoApplySession;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import lk.jiat.ee.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@ApplicationScoped
public class AppIdentitystore implements IdentityStore {
    private static final Map<String, User> USER = new HashMap<>();

    static {
        USER.put("ravishka",new User("123", Set.of("ADMIN")));
        USER.put("indraji",new User("123", Set.of("ADMIN","USER")));
        USER.put("ranaweera",new User("123", Set.of("USER")));
    }


    @Override
    public CredentialValidationResult validate(Credential credential) {
        System.out.println("App Identity store validate....");

        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential upc = (UsernamePasswordCredential) credential;
            System.out.println("UPC GetCaller : "+upc.getCaller());
            User user = USER.get(upc.getCaller());
            if (user != null && user.getPassword().equals(upc.getPasswordAsString())) {
                return new CredentialValidationResult(upc.getCaller(),user.getRoles());
            }
        }
        return CredentialValidationResult.INVALID_RESULT;
    }
}
