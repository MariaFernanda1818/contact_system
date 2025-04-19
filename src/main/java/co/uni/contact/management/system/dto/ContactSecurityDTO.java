package co.uni.contact.management.system.dto;

import co.uni.contact.management.system.utils.constants.Constantes;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Clase que representa los detalles de seguridad de un contacto para la autenticación y autorización.
 */
@Data
public class ContactSecurityDTO extends ContactDTO implements UserDetails {

    /**
     * Contraseña del contacto utilizada para la autenticación.
     */
    private String password;

    public ContactSecurityDTO(ContactDTO contactDTO) {
        super();
        setIdContact(contactDTO.getIdContact());
        setFirstName(contactDTO.getFirstName());
        setLastName(contactDTO.getLastName());
        setEmailAddress(contactDTO.getEmailAddress());
        setPhone(contactDTO.getPhone());
        setAddress(contactDTO.getAddress());
        setBirthDate(contactDTO.getBirthDate());
        this.password = contactDTO.getHashPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Constantes.ROL_CONTACTO));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getEmailAddress();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
