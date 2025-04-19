package co.uni.contact.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * DTO para representar una respuesta general en la aplicación.
 * <p>
 * Esta clase se utiliza para encapsular información estándar en las respuestas
 * de las APIs, incluyendo el estado HTTP, un mensaje, y datos adicionales.
 * </p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaGeneralDTO implements Serializable {

    private HttpStatus status;

    private Object data;

    private String message;
}
