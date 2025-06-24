package lk.jiat.ee.bank.ejb.remote;


import jakarta.ejb.Local;

@Local
public interface LoginService {
    boolean loginUser( String email, String password);
}
