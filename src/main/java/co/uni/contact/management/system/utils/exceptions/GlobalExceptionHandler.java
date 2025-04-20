package co.uni.contact.management.system.utils.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static co.uni.contact.management.system.utils.constants.Constantes.*;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    /**
     * Maneja ResourceNotFoundException para devolver un 404 personalizado.
     *
     * @param ex la excepción lanzada.
     * @param request el objeto WebRequest.
     * @return ResponseEntity con cuerpo de error 404.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put(TIMESTAMP_KEY, LocalDateTime.now());
        body.put(PATH_KEY, request.getDescription(false));
        body.put(STATUS_KEY, HttpStatus.NOT_FOUND.value());
        body.put(MESSAGE_KEY, ex.getMessage());
        body.put(ERRORS_KEY, "Resource not found");

        log.error("Resource Not Found Exception: {}", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja todas las demás excepciones que ocurren en la aplicación,
     * incluidas las excepciones genéricas, errores HTTP y errores de validación.
     *
     * @param ex      la excepción lanzada.
     * @param request el objeto WebRequest que contiene detalles de la solicitud.
     * @return un ResponseEntity con un cuerpo de respuesta que describe el error.
     */
    @ExceptionHandler({Exception.class, HttpClientErrorException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put(TIMESTAMP_KEY, LocalDateTime.now());
        body.put(PATH_KEY, request.getDescription(false));

        if (ex instanceof MethodArgumentNotValidException) {
            // Manejo de validaciones fallidas
            log.error(ERRORES_VALIDACIONES, ex.getMessage());
            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            body.put(STATUS_KEY, HttpStatus.BAD_REQUEST.value());
            body.put(MESSAGE_KEY, VALIDATION_ERRORS_MESSAGE);
            body.put(ERRORS_KEY, errors);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

        } else if (ex instanceof HttpClientErrorException) {
            // Manejo de errores de cliente HTTP
            log.error(RECURSO_NO_ENCONTRADO, ex.getMessage());
            body.put(STATUS_KEY, HttpStatus.NOT_FOUND.value());
            body.put(MESSAGE_KEY, NOT_FOUND_ERROR);
            body.put(ERRORS_KEY, ex.getMessage());
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

        } else {
            // Manejo de excepciones genéricas
            log.error(ERROR_INESPERADO, ex.getMessage());
            body.put(STATUS_KEY, HttpStatus.INTERNAL_SERVER_ERROR.value());
            body.put(MESSAGE_KEY, INTERNAL_SERVER_ERROR);
            body.put(ERRORS_KEY, ex.getMessage());
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
