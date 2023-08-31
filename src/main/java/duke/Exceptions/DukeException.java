package duke.exceptions;

/**
 * Exceptions of Duke object, errors which duke is likely to encounter and what is done
 * to catch and deal with the errors
 */
public class DukeException extends Throwable {
    /**
     * Constructor of the duke class
     * @param message is the message that the bot returns when encountering the
     *                error
     */
    public DukeException(String message) {

        super(message);
    }

    /**
     * Message given by the bot when encountering them
     * @return a string containing the messgae
     */
    @Override
    public String getMessage() {

        return String.format("OOPS!!! %s", super.getMessage());
    }
}
