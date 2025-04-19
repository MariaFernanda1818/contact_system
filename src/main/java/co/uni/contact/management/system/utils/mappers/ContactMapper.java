package co.uni.contact.management.system.utils.mappers;

import co.uni.contact.management.system.dto.ContactDTO;
import co.uni.contact.management.system.entity.ContactEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Interfaz que define los métodos de mapeo entre las entidades {@link co.uni.contact.management.system.entity.ContactEntity} y los DTOs {@link co.uni.contact.management.system.dto.ContactDTO}.
 * <p>
 * Utiliza MapStruct para generar automáticamente las implementaciones de los métodos de mapeo.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface ContactMapper {

    /**
     * Convierte una entidad {@link ContactEntity} a un objeto DTO {@link ContactDTO}.
     *
     * @param entity la entidad {@link ContactEntity} a convertir.
     * @return el objeto {@link ContactDTO} correspondiente.
     */
    ContactDTO entityToDto(ContactEntity entity);

    /**
     * Convierte un objeto DTO {@link ContactDTO} a una entidad {@link ContactEntity}.
     *
     * @param dto el objeto {@link ContactDTO} a convertir.
     * @return la entidad {@link ContactEntity} correspondiente.
     */
    ContactEntity dtoToEntity(ContactDTO dto);

    /**
     * Convierte una lista de entidades {@link ContactEntity} a una lista de objetos DTO {@link ContactDTO}.
     *
     * @param entities la lista de entidades {@link ContactEntity} a convertir.
     * @return la lista de objetos {@link ContactDTO} correspondiente.
     */
    List<ContactDTO> listEntityToListDto(List<ContactEntity> entities);

    /**
     * Convierte una lista de objetos DTO {@link ContactDTO} a una lista de entidades {@link ContactEntity}.
     *
     * @param dtos la lista de objetos {@link ContactDTO} a convertir.
     * @return la lista de entidades {@link ContactEntity} correspondiente.
     */
    List<ContactEntity> listDtoToListEntity(List<ContactDTO> dtos);
}
