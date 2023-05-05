package ztw.nextapp.exceptions;

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
