package infrastructure.adapter.output.persistance.mapper;

import domain.model.BankAccount;
import infrastructure.adapter.input.rest.dto.BankAccountRequest;
import infrastructure.adapter.input.rest.dto.BankAccountResponse;
import infrastructure.adapter.output.persistance.entity.BankAccountEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    @Mapping(target = "client", source = "clientEntity")
    BankAccount toDomain(BankAccountEntity entity);

    // Request to Domain (for new bank account)
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "balance", source = "balance")
    BankAccount toDomain(BankAccountRequest request);

    // Domain to Entity
    @Mapping(target = "id", source = "id")
    //@Mapping(target = "name", source = "name")
    @Mapping(target = "balance", source = "balance")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "clientEntity", source = "client")
    BankAccountEntity toEntity(BankAccount domain);

    // Domain to Response
    @Mapping(target = "id", source = "id")
    //@Mapping(target = "name", source = "name")
    @Mapping(target = "balance", source = "balance")
    @Mapping(target = "accountNumber", source = "accountNumber")
    BankAccountResponse toResponse(BankAccount domain);
}
