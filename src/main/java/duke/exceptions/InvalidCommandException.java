package duke.exceptions;

/**
 * This exception is thrown when the user input does not match valid command.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("Sorry, I don't understand what you mean ><");
    }
}
