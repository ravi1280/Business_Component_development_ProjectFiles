package lk.jiat.ee.bank.ejb.remote;

import java.rmi.RemoteException;

public interface TransferService {
    void transferAmount(String sourceAccountNumber, String destinationAccountNumber, double amount) ;
}
