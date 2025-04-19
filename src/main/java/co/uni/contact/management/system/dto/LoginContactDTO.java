package co.uni.contact.management.system.dto;

import lombok.Data;

/**
 * DTO que representa los datos necesarios para el inicio de sesión de un contacto.
 * <p>
 * Este objeto contiene las credenciales del contacto, como el correo electrónico
 * y la contraseña, requeridas para realizar la autenticación en el sistema.
 * </p>
 */
@Data
public class LoginContactDTO {

    private String emailAddress;

    private String password;
}
