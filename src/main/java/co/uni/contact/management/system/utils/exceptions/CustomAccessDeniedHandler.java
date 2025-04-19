package co.uni.contact.management.system.utils.exceptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static co.uni.contact.management.system.utils.constants.Constantes.*;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * Método que maneja las solicitudes con acceso denegado.
     * <p>
     * Este método se invoca cuando un usuario intenta acceder a un recurso al que no tiene permisos.
     * Devuelve una respuesta HTTP 403 (Prohibido) con un mensaje de error en formato JSON.
     * </p>
     *
     * @param request               el objeto {@link HttpServletRequest} que representa la solicitud.
     * @param response              el objeto {@link HttpServletResponse} que representa la respuesta.
     * @param accessDeniedException la excepción que contiene detalles del acceso denegado.
     * @throws IOException      si ocurre un error de E/S al escribir la respuesta.
     * @throws ServletException si ocurre un error relacionado con el manejo de la solicitud.
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // Devolver un código 403 con un mensaje personalizado
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.setStatus(RESPONSE_STATUS_FORBIDDEN);
        response.getWriter().write(ACCESS_DENIED_MESSAGE);
    }
}
