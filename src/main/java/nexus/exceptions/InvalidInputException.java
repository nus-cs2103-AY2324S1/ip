package nexus.exceptions;

/**
 * Custom exception for invalid user input.
 */
public class InvalidInputException extends Exception {
    public InvalidInputException(String msg) {
        super(msg);
    }
}
