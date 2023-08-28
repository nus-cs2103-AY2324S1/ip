package duke;

/**
 * InvalidInputException encapsulates the error when the user inputs an invalid command
 */
public class InvalidInputException extends Exception {
    public InvalidInputException() {
        super("Invalid command given.");
    }
}
