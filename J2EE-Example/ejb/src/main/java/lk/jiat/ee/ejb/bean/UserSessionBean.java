package lk.jiat.ee.ejb.bean;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.jiat.ee.core.model.User;
import lk.jiat.ee.core.service.UserService;

@Stateless
public class UserSessionBean implements UserService {
    @PersistenceContext
    private EntityManager em;


    @Override
    public User getUserByID(long id) {
       User user = em.find(User.class, id);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
      User  user =  em.createNamedQuery("User.FindByEmail",User.class)
              .setParameter("email", email)
              .getSingleResult();
        return user;
//        em.createNamedQuery("User.findByEmail").setParameter(1, email);
    }

    @Override
    public void createUser(User user) {
        em.persist(user);

    }

    @Override
    public void updateUser(User user) {
        em.merge(user);

    }

    @Override
    public void deleteUser(User user) {
        em.remove(user);

    }

    @Override
    public boolean userExists(String email, String password) {
        User user = em.createNamedQuery("User.FindByEmailAndPassword", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();

        return user != null;
    }
}
