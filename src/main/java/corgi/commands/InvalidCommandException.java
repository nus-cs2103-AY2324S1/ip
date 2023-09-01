package corgi.commands;

/**
 * Exception thrown when an invalid command is provided.
 */
public class InvalidCommandException extends CommandException{

    /**
     * Constructs a new InvalidCommandException with a default error message.
     */
    public InvalidCommandException() {
        super("Invalid command is provided!");
    }
}
