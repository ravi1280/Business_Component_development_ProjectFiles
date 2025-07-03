package lk.jiat.ee.security.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;


@ApplicationScoped
public class AppIdentityStore implements IdentityStore {
    @PersistenceContext
    private EntityManager em;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        return IdentityStore.super.validate(credential);
    }
}
