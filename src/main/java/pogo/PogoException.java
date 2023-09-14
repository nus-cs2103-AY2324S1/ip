package pogo;

/**
 * Represents an exception that occurs in Pogo.
 * Superclass of all Pogo exceptions
 */
public class PogoException extends Exception {
    public PogoException(String message) {
        super(message);
    }
}
