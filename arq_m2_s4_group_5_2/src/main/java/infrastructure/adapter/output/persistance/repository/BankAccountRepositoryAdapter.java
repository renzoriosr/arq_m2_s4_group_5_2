package infrastructure.adapter.output.persistance.repository;

import application.port.output.BankAccountRepositoryPort;
import domain.model.BankAccount;
import infrastructure.adapter.output.persistance.entity.BankAccountEntity;
import infrastructure.adapter.output.persistance.mapper.BankAccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j

public class BankAccountRepositoryAdapter implements BankAccountRepositoryPort {

    private final BankAccountJpaRepository jpaRepository;
    private final BankAccountMapper mapper;

    @Override
    public BankAccount save(BankAccount bankAccount) {
        log.info("Saving bank account: {}", bankAccount);

        BankAccountEntity entity = this.mapper.toEntity(bankAccount);

        BankAccountEntity savedEntity = this.jpaRepository.save(entity);
        BankAccount savedBankAccount = this.mapper.toDomain(savedEntity);

        log.info("Saved bank account via Spring Data: {}", savedBankAccount);

        return savedBankAccount;
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        return this.jpaRepository.findById(id).map(this.mapper::toDomain);
    }

    @Override
    public BankAccount findByAccountNumber(String accountNumber) {
        BankAccountEntity entity = this.jpaRepository.findByAccountNumber(accountNumber);
        return this.mapper.toDomain(entity);
    }
}
