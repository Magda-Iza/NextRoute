package ztw.nextapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not enough vehicles")
public class NotEnoughVehiclesException extends RuntimeException {
    public NotEnoughVehiclesException() {
        super();
    }

    public NotEnoughVehiclesException(String message) {
        super(message);
    }

    public NotEnoughVehiclesException(String message, Throwable cause) {
        super(message, cause);
    }
}
