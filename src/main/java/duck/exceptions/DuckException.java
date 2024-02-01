package duck.exceptions;

/**
 * Throws Duck Exception for invalid command.
 */
public class DuckException extends Exception {

    public DuckException(String message) {
        super(message);
    }

}
