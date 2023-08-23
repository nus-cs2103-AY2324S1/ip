package commands;

/**
 * Exception thrown when an command description is not provided.
 */
public class EmptyDescException extends CommandException{
    
    /**
     * Constructs a new EmptyDescException with a default error message.
     */
    public EmptyDescException() {
        super("No description is provided for the command!");
    }
}
