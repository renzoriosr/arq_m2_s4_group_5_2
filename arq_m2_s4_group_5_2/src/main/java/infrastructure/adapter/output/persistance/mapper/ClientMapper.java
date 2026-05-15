package infrastructure.adapter.output.persistance.mapper;

import domain.model.Client;
import infrastructure.adapter.output.persistance.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client toDomain(ClientEntity entity);

    // Domain to Entity
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "createdAt", ignore = true)
    ClientEntity toEntity(Client domain);
}
