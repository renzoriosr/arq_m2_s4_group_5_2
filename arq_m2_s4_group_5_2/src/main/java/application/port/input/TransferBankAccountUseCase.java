package application.port.input;

import domain.model.BankAccount;

public interface TransferBankAccountUseCase {
    BankAccount transfer (String sourceAccountNumber, String destinationAccountNumber, Float amount);
}
