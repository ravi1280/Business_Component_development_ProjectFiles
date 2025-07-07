package lk.jiat.ee.core.exception;


import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class InvalidParaException extends RuntimeException{
    public InvalidParaException(String message) {
        super(message);
    }
}
