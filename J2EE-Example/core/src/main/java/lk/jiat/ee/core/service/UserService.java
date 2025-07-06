package lk.jiat.ee.core.service;


import jakarta.ejb.Remote;
import lk.jiat.ee.core.model.User;


@Remote
public interface UserService {

    User getUserByID(long id);
    User getUserByEmail(String email);

    void createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    boolean userExists(String email, String password);
}
