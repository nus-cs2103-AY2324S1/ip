package corgi.commands;

/**
 * Represents an exception that is thrown when an error occurs during the execution of a command.
 * It is used to indicate that the execution of a command has failed.
 */
public class CommandExecutionException extends CommandException{
    /**
     * Initializes a new CommandExecutionException with the specified error message.
     *
     * @param msg The error message describing the cause of the exception.
     */
    public CommandExecutionException(String msg) {
        super(msg);
    }
}
