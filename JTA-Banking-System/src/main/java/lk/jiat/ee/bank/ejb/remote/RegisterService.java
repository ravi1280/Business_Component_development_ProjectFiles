package lk.jiat.ee.bank.ejb.remote;

import jakarta.ejb.Local;
import jakarta.ejb.Lock;

@Local
public interface RegisterService {
    void registerUser(String name,String email, String password);
}
