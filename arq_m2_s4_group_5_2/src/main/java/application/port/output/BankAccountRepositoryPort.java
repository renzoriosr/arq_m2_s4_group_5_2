package application.port.output;

import domain.model.BankAccount;

import java.util.Optional;

public interface BankAccountRepositoryPort {
    BankAccount save(BankAccount bankAccount);
    Optional<BankAccount> findById(Long id);
    BankAccount findByAccountNumber(String accountNumber);
}
