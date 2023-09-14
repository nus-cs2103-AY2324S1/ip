package duke;

/**
 * Signals that given data does not match any command.
 */
public class InvalidInputException extends Exception {

    public InvalidInputException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
