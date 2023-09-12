package alyssa.exceptions;

/**
 * This class represents the exception which is thrown when a correct command is given with
 * incomplete/invalid arguments.
 */
public class AlyssaArgumentException extends AlyssaException {
    public AlyssaArgumentException(String message) {
        super(message);
    }
}
