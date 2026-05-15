package infrastructure.adapter.output.persistance.repository;

import application.port.output.ClientRepositoryPort;
import domain.model.Client;
import infrastructure.adapter.output.persistance.entity.ClientEntity;
import infrastructure.adapter.output.persistance.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ClientRepositoryAdapter implements ClientRepositoryPort {

    private final ClientJpaRepository jpaRepository;
    private final ClientMapper clientMapper;

    @Override
    public boolean existByName(String name) {
        log.info("Finding account by name cantaining: {}", name);
        return this.jpaRepository.existsByName(name);
    }

    @Override
    public Client findByName(String name) {
        log.info("Finding client by name: {}", name);

        ClientEntity entity = this.jpaRepository.findByName(name);
        return this.clientMapper.toDomain(entity);
    }

    @Override
    public Client save(Client client) {
        log.info("Saving client: {}", client);

        ClientEntity entity = this.clientMapper.toEntity(client);
        ClientEntity savedEntity = this.jpaRepository.save(entity);
        Client savedClient = this.clientMapper.toDomain(savedEntity);

        log.info("Saved client via Spring Data: {}", savedClient);

        return savedClient;
    }
}
