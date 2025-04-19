package co.uni.contact.management.system.utils.exceptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static co.uni.contact.management.system.utils.constants.Constantes.*;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setContentType(RESPONSE_CONTENT_TYPE);

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            // Ya había un usuario autenticado, pero no tiene permisos -> 403
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("{\"error\": \"Acceso denegado: No tienes permisos suficientes.\"}");
        } else {
            // No autenticado -> 401
            response.setStatus(RESPONSE_STATUS_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"" + AUTHENTICATION_FAILED_MESSAGE + authException.getMessage() + "\"}");
        }
    }
}
