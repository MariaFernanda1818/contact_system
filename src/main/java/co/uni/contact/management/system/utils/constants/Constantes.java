package co.uni.contact.management.system.utils.constants;

import jakarta.servlet.http.HttpServletResponse;

public class Constantes {

    // Roles
    public static final String ROL_CONTACTO = "CONTACTO";

    // Constantes de configuración
    public static final String ERROR_USUARIO_NO_ENCONTRADO = "Error al buscar el usuario: ";
    public static final String ORIGEN_DESARROLLO = "http://localhost:4200";

    // Rutas
    public static final String V3_API_DOCS_PATH = "/v3/api-docs/**";
    public static final String SWAGGER_UI_PATH = "/doc/swagger-ui/***";
    public static final String CONTACTO_PATH = "/contacto/**";


    // Claims y mensajes relacionados con JWT
    public static final String CLAIM_CODIGO_CONTACTO = "codigoContacto";
    public static final String ERROR_IDENTIFICADOR_NO_VALIDO = "El token no contiene un identificador válido.";
    public static final long EXPIRATION_TIME_IN_MILLIS = 24 * 60 * 60 * 1000; // 24 horas

    // Respuesta HTTP: contenido y estados
    public static final String RESPONSE_CONTENT_TYPE = "application/json";
    public static final int RESPONSE_STATUS_FORBIDDEN = HttpServletResponse.SC_FORBIDDEN;
    public static final String ACCESS_DENIED_MESSAGE = "{\"error\": \"Acceso denegado: No tienes permiso para acceder a este recurso.\"}";

    public static final int RESPONSE_STATUS_UNAUTHORIZED = HttpServletResponse.SC_UNAUTHORIZED;
    public static final String AUTHENTICATION_FAILED_MESSAGE = "Autenticación fallida: ";


    // Prefijo y tamaño de tokens Bearer
    public static final String BEARER_PREFIX = "Bearer ";
    public static final int SIZE_BEARER = 7;

}
