package co.uni.contact.management.system.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interfaz para la gestión de tokens JWT.
 * <p>
 * Define métodos para generar, validar y extraer información de tokens JWT.
 * </p>
 */
public interface IJwtService {

    /**
     * Genera un token JWT para el usuario especificado.
     *
     * @param userDetails los detalles del usuario que se incluirán en el token.
     * @return el token JWT generado.
     */
    String getToken(UserDetails userDetails);

    /**
     * Extrae el nombre de usuario (subject) de un token JWT.
     *
     * @param token el token JWT del que se extrae el nombre de usuario.
     * @return el nombre de usuario contenido en el token.
     */
    String getUsernameFromToken(String token);

    /**
     * Verifica si un token JWT es válido para el usuario especificado.
     *
     * @param token       el token JWT a validar.
     * @param userDetails los detalles del usuario para comparar.
     * @return true si el token es válido, false en caso contrario.
     */
    boolean isTokenValid(String token, UserDetails userDetails);
}
