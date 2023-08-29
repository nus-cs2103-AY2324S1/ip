package exceptions;

/**
 * Custom exception class to represent an IO exception.
 * This exception is thrown when there is an IO exception.
 */
public class IOException extends RuntimeException {
    public IOException(String msg) {
        super(msg);
    }
}
