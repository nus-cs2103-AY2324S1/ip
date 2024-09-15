package emiya.emiyaexception;

/**
 * Base exception that is the superclass of all other exceptions that are written.
 */
public class EmiyaException extends Exception {
    public EmiyaException(String message) {
        super(message);
    }

}
