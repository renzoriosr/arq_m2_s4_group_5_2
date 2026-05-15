package domain.exception;

public class InvalidClientDataException  extends RuntimeException {
    public InvalidClientDataException(String message) {
        super("Invalid client data: " + message);
    }
}
