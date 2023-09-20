package alyssa.exceptions;

/**
 * A generic exception. All custom exceptions extend from this.
 */
public class AlyssaException extends RuntimeException {
    public AlyssaException(String message) {
        super(message);
    }
}
