package corgi.commands;

/**
 * Parent exception class for chatbot command.
 */
public class CommandException extends Exception{
    /**
     * Constructs a new CommandException using provided error message.
     * 
     * @param msg Error message provided
     */
    public CommandException(String msg) {
        super(msg);
    }
}
