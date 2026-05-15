package application.port.input;

import domain.model.BankAccount;

public interface CreateBankAccountUseCase {
    BankAccount execute(BankAccount newBankAccount, String name, String document);
}
