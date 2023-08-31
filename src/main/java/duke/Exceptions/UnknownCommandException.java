package duke.Exceptions;

/**
 * The UnknownCommandException, which is basically what the bot does when
 * a random command is given
 */
public class UnknownCommandException extends DukeException {
    /**
     * Constructor of the UnknownCommandException used to allow the bot
     * to catch error when a random command is given
     * @param message is the message given when random commands are given
     */
    public UnknownCommandException(String message) {
        super(message);
    }
}
