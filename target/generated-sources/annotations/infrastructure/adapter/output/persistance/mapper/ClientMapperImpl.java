package infrastructure.adapter.output.persistance.mapper;

import domain.model.Client;
import infrastructure.adapter.output.persistance.entity.ClientEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-13T10:53:08-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client toDomain(ClientEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.id( entity.getId() );
        client.name( entity.getName() );
        client.email( entity.getEmail() );
        client.document( entity.getDocument() );
        client.createdAt( entity.getCreatedAt() );

        return client.build();
    }

    @Override
    public ClientEntity toEntity(Client domain) {
        if ( domain == null ) {
            return null;
        }

        ClientEntity clientEntity = new ClientEntity();

        if ( domain.getId() != null ) {
            clientEntity.setId( domain.getId() );
        }
        clientEntity.setName( domain.getName() );
        clientEntity.setEmail( domain.getEmail() );
        clientEntity.setDocument( domain.getDocument() );

        return clientEntity;
    }
}
