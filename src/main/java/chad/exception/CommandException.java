package chad.exception;

/**
 * Represents an error that occurred during the initialisation of a command.
 */
public class CommandException extends ChadException {

    public CommandException(String format) {
        super("Oops! Invalid input for your command.\nValid Format: " + format);
    }
}
