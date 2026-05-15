package domain.exception;

public class BankAccountNotFoundException extends RuntimeException {
    public BankAccountNotFoundException(Long id) {
        super("Bank account not found with ID: " + id);
    }
}
