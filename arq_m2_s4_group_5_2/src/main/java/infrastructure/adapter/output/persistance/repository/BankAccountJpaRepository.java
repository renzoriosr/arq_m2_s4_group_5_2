package infrastructure.adapter.output.persistance.repository;

import infrastructure.adapter.output.persistance.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountJpaRepository extends JpaRepository<BankAccountEntity, Long> {
    BankAccountEntity findByAccountNumber(String accountNumber);
}
