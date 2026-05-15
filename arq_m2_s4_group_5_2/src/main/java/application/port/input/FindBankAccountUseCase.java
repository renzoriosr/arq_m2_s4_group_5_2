package application.port.input;

import domain.model.BankAccount;

public interface FindBankAccountUseCase {
    BankAccount findBankAccountById(Long id);
}
