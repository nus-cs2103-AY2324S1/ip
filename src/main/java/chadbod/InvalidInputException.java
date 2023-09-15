package chadbod;

/**
 * Custom exception class for handling invalid user input exceptions in the ChadBod application.
 */
public class InvalidInputException extends ChadBodException {
    public InvalidInputException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
