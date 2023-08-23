package commands;

/**
 * Exception thrown when format of the command description is invalid.
 */
public class InvalidDescFormatException extends CommandException{

    /**
     * Constructs a new InvalidDescFormatException with a default error message.
     */
    public InvalidDescFormatException() {
        super("The format of the command description if invalid!");
    }
}
