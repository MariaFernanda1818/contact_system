package co.uni.contact.management.system.utils.exceptions;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
