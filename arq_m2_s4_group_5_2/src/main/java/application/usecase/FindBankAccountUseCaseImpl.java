package application.usecase;

import application.port.input.FindBankAccountUseCase;
import application.port.output.BankAccountRepositoryPort;
import domain.exception.BankAccountNotFoundException;
import domain.model.BankAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class FindBankAccountUseCaseImpl implements FindBankAccountUseCase {

    private final BankAccountRepositoryPort bankAccountRepositoryPort;

    @Override
    @Transactional(readOnly = true)
    public BankAccount findBankAccountById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }

        return bankAccountRepositoryPort.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException(id));
    }
}
