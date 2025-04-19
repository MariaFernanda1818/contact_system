package co.uni.contact.management.system.service;

import co.uni.contact.management.system.dto.LoginContactDTO;
import co.uni.contact.management.system.dto.RespuestaGeneralDTO;

/**
 * Interfaz para el servicio de autenticación de contactos.
 * <p>
 * Define el contrato para realizar el proceso de inicio de sesión
 * de un contacto en el sistema.
 * </p>
 */
public interface ILoginContactService {

    /**
     * Realiza el inicio de sesión de un contacto.
     *
     * @param loginContactDTO el objeto {@link LoginContactDTO} que contiene las credenciales
     *                        del contacto (correo y contraseña).
     * @return un objeto {@link RespuestaGeneralDTO} que contiene el estado, mensaje y datos
     * del resultado de la operación, incluyendo un token de autenticación si el
     * inicio de sesión es exitoso.
     */
    RespuestaGeneralDTO loginContact(LoginContactDTO loginContactDTO);
}
