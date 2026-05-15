package domain.exception;

public class InvalidBankAccountDataException extends RuntimeException{

    public InvalidBankAccountDataException(String message) {
        super("Invalid bank account data: " + message);
    }
}
