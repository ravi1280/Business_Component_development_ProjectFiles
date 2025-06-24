package lk.jiat.ee.bank.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.jiat.ee.bank.ejb.remote.LoginService;
import lk.jiat.ee.bank.entity.User;

@Stateless
public class LoginServiceBean implements LoginService {

    @PersistenceContext(unitName = "BankPU")
    private EntityManager em;

    @Override
    public boolean loginUser(String email, String password) {
       User user = em.createNamedQuery("User.findByEmailAndPassword", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();

       return user != null;

    }
}
