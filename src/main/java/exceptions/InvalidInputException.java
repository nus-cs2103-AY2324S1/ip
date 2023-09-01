package exceptions;

/**
 * Exception class for invalid inputs
 */
public class InvalidInputException extends BocchiException {
    public InvalidInputException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
