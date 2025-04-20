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

    // Mensajes de error en logs
    public static final String ERRORES_VALIDACIONES = "Errores de validación: {}";
    public static final String RECURSO_NO_ENCONTRADO = "Recurso no encontrado: {}";
    public static final String ERROR_INESPERADO = "Error inesperado: {}";

    // Claves y mensajes de respuesta
    public static final String TIMESTAMP_KEY = "timestamp";
    public static final String PATH_KEY = "path";
    public static final String STATUS_KEY = "status";
    public static final String MESSAGE_KEY = "message";
    public static final String ERRORS_KEY = "errors";
    public static final String VALIDATION_ERRORS_MESSAGE = "Errores de validación";
    public static final String NOT_FOUND_ERROR = "No encontrado";
    public static final String INTERNAL_SERVER_ERROR = "Error interno del servidor";

    // Prefijo y tamaño de tokens Bearer
    public static final String BEARER_PREFIX = "Bearer ";
    public static final int SIZE_BEARER = 7;

}
