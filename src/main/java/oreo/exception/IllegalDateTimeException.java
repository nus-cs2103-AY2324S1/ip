package oreo.exception;

import java.time.DateTimeException;

public class IllegalDateTimeException extends Exception {
    private String message;

    /**
     * Constructor for IllegalDateTimeException.
     *
     * @param message message to include.
     */
    public IllegalDateTimeException(String message) {
        super(message);
    }
}
