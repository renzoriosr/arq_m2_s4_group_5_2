package infrastructure.adapter.output.persistance.repository;

import infrastructure.adapter.output.persistance.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientJpaRepository extends JpaRepository<ClientEntity, Long> {

    ClientEntity findByName(String name);

    boolean existsByName(String name);
}
