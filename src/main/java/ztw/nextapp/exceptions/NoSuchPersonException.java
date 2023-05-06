package ztw.nextapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchPersonException extends RuntimeException {
    public NoSuchPersonException() {
        super();
    }

    public NoSuchPersonException(String message) {
        super(message);
    }

    public NoSuchPersonException(String message, Throwable cause) {
        super(message, cause);
    }
}
