package infrastructure.adapter.output.persistance.mapper;

import domain.model.BankAccount;
import domain.model.Client;
import infrastructure.adapter.input.rest.dto.BankAccountRequest;
import infrastructure.adapter.input.rest.dto.BankAccountResponse;
import infrastructure.adapter.output.persistance.entity.BankAccountEntity;
import infrastructure.adapter.output.persistance.entity.ClientEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-13T11:13:27-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class BankAccountMapperImpl implements BankAccountMapper {

    @Override
    public BankAccount toDomain(BankAccountEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BankAccount.BankAccountBuilder bankAccount = BankAccount.builder();

        bankAccount.client( clientEntityToClient( entity.getClientEntity() ) );
        bankAccount.id( entity.getId() );
        bankAccount.accountNumber( entity.getAccountNumber() );
        bankAccount.balance( entity.getBalance() );
        bankAccount.state( entity.getState() );
        bankAccount.createdAt( entity.getCreatedAt() );
        bankAccount.updatedAt( entity.getUpdatedAt() );

        return bankAccount.build();
    }

    @Override
    public BankAccount toDomain(BankAccountRequest request) {
        if ( request == null ) {
            return null;
        }

        BankAccount.BankAccountBuilder bankAccount = BankAccount.builder();

        bankAccount.balance( request.getBalance() );

        return bankAccount.build();
    }

    @Override
    public BankAccountEntity toEntity(BankAccount domain) {
        if ( domain == null ) {
            return null;
        }

        BankAccountEntity bankAccountEntity = new BankAccountEntity();

        bankAccountEntity.setId( domain.getId() );
        bankAccountEntity.setBalance( domain.getBalance() );
        bankAccountEntity.setClientEntity( clientToClientEntity( domain.getClient() ) );
        bankAccountEntity.setAccountNumber( domain.getAccountNumber() );
        bankAccountEntity.setState( domain.getState() );

        return bankAccountEntity;
    }

    @Override
    public BankAccountResponse toResponse(BankAccount domain) {
        if ( domain == null ) {
            return null;
        }

        BankAccountResponse.BankAccountResponseBuilder bankAccountResponse = BankAccountResponse.builder();

        if ( domain.getId() != null ) {
            bankAccountResponse.id( domain.getId() );
        }
        bankAccountResponse.balance( domain.getBalance() );
        bankAccountResponse.accountNumber( domain.getAccountNumber() );

        return bankAccountResponse.build();
    }

    protected Client clientEntityToClient(ClientEntity clientEntity) {
        if ( clientEntity == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.id( clientEntity.getId() );
        client.name( clientEntity.getName() );
        client.email( clientEntity.getEmail() );
        client.document( clientEntity.getDocument() );
        client.createdAt( clientEntity.getCreatedAt() );

        return client.build();
    }

    protected ClientEntity clientToClientEntity(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientEntity clientEntity = new ClientEntity();

        if ( client.getId() != null ) {
            clientEntity.setId( client.getId() );
        }
        clientEntity.setName( client.getName() );
        clientEntity.setEmail( client.getEmail() );
        clientEntity.setDocument( client.getDocument() );
        clientEntity.setCreatedAt( client.getCreatedAt() );

        return clientEntity;
    }
}
