package duke.userio;

/**
 * Custom exception created to raise invalid user input problem.
 */
public class InvalidUserInputException extends Exception {
    public InvalidUserInputException() {
        super("Invalid user input is detected");
    }
}
