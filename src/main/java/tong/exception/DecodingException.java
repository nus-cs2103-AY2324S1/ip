package tong.exception;

/**
 * Signals an error during decoding of String representation in the data file
 * to a tong.task.Task.
 */
public class DecodingException extends Exception {
    public DecodingException(String message) {
        super(message);
    }
}
