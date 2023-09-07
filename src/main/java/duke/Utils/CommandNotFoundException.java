package duke.Utils;

/**
 * The CommandNotFoundException class represents an exception that is thrown when
 * a command is not found or recognized by the Duke application.
 */
public class CommandNotFoundException extends DukeException {
    /**
     * Constructs a new CommandNotFoundException with a default error message.
     * The error message indicates that there is no such command.
     */
    protected CommandNotFoundException() {
        super("I'm sorry, but there is no such command.");
    }
}