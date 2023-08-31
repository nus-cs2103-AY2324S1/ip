package duke.components;

/**
 * Exception class for the bot.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     *
     * @param msg The error message to be displayed when
     * an error is encountered.
     */
    public DukeException(String msg) {
        super(msg);
    }
}