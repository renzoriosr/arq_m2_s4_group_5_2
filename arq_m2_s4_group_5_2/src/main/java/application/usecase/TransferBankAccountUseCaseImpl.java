package application.usecase;

import application.port.input.TransferBankAccountUseCase;
import application.port.output.BankAccountRepositoryPort;
import application.port.output.LoggingPort;
import domain.exception.NotEnoughBalanceBankAccountException;
import domain.model.BankAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class TransferBankAccountUseCaseImpl implements TransferBankAccountUseCase {

    private final BankAccountRepositoryPort bankAccountRepositoryPort;
    private final LoggingPort loggingPort;

    @Override
    public BankAccount transfer(String sourceAccountNumber, String destinationAccountNumber, Float amount) {
        BankAccount source = bankAccountRepositoryPort.findByAccountNumber(sourceAccountNumber);

        boolean isEnoughBalance = validateIfEnoughBalance(source.getBalance(), amount);

        if (!isEnoughBalance) {
            throw new NotEnoughBalanceBankAccountException("Bank account balance " +  source.getBalance().toString() +
                    "is not enough to transfer " + amount.toString());
        }

        BankAccount destination = bankAccountRepositoryPort.findByAccountNumber(destinationAccountNumber);

        source.setBalance( source.getBalance() - amount );
        destination.setBalance( destination.getBalance() + amount);

        bankAccountRepositoryPort.save(source);

        loggingPort.saveLogInConsole(destination.getAccountNumber(), amount.toString());

        return bankAccountRepositoryPort.save(destination);
    }

    private boolean validateIfEnoughBalance(Float balance, Float amount) {
        return balance >= amount;
    }
}
