package duke.exception;

/**
 * Represents an error that occurred during the initialisation of a command.
 */
public class CommandException extends DukeException {

    public CommandException(String format) {
        super("Oops! Invalid input for your command.\nValid Format: " + format);
    }
}
