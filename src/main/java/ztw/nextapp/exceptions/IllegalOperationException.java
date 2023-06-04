package ztw.nextapp.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@Getter
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Illegal operation")
public class IllegalOperationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String title = "Illegal operation";

    public IllegalOperationException() {
        super("Operations is illegal");
    }
}
