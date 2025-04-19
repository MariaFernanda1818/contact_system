package co.uni.contact.management.system.repository;

import co.uni.contact.management.system.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestionar las operaciones de acceso a datos de la entidad {@link ContactEntity}.
 * Proporciona métodos estándar de CRUD a través de {@link JpaRepository}.
 */
@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    /**
     * Busca un contacto por su dirección de correo electrónico.
     *
     * @param emailAddress el correo electrónico del contacto a buscar.
     * @return un {@link Optional} que contiene el contacto si se encuentra.
     */
    Optional<ContactEntity> findByEmailAddress(String emailAddress);
}
