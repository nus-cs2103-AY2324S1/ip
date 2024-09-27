package duke;

/**
 * Represents an exception that is unique to the Richie application
 */
public class RichieException extends Exception {

    /**
     * Constructs a RichieException with a default error message telling the user that it does not understand the user
     * input
     */
    public RichieException() {
        super("OOPS!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Constructs a RichieException with a specified message
     * @param message Error message to be shown to the user
     */
    public RichieException(String message) {
        super(message);
    }
}
