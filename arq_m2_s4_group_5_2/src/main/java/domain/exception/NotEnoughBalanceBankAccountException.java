package domain.exception;

public class NotEnoughBalanceBankAccountException extends RuntimeException{
    public NotEnoughBalanceBankAccountException(String message) {
        super("Not Enough Balance: " + message);
    }
}
