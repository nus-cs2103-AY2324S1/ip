package duke.exception;

/**
 * Exceptions of Duke object, errors which duke is likely to encounter and what
 * is done
 * to catch and deal with the errors
 */
public class DukeException extends Throwable {
    /**
     * Represents a constructor of the duke class
     * 
     * @param message is the message that the bot returns when encountering the
     *                error
     */
    public DukeException(String message) {

        super(message);
    }

    /**
     * Represents a message given by the bot when encountering them
     * 
     * @return a string containing the message given by the bot
     */
    @Override
    public String getMessage() {

        return String.format("PIKA PIKA!!! %s", super.getMessage());
    }
}
