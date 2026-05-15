package application.port.output;

import domain.model.Client;

import java.util.Optional;

public interface ClientRepositoryPort {
    boolean existByName(String name);
    Client findByName(String name);
    Client save(Client client);
}
