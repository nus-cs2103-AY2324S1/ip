package exceptions;

/**
 * Parent class for all Barbie-related Exceptions.
 */
public class BarbieException extends Exception{

    /**
     * Constructor for a BarbieException for child classes to implement.
     * @param message to print
     */
    public BarbieException(String message) {
        super(message);
    }
}
