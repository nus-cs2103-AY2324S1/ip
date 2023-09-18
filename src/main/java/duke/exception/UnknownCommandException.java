package duke.exception;

/**
 * Subclass of dukeException
 *
 */
public class UnknownCommandException extends DukeException {
    /**
     * Represents a constructor of the UnknownCommandException used to allow the bot
     * to catch error when a random command is given
     * 
     * @param message is the message given when random commands are given
     */
    public UnknownCommandException(String message) {
        super(message);
    }
}
