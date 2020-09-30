package nl.rabobank.powerofattorney.domain.exceptions;

public class InactiveEntityException extends RuntimeException {
    public InactiveEntityException(String message) {
        super(message);
    }

    public InactiveEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
