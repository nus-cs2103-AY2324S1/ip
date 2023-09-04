package duke.exception;

/**
 * General exception thrown by duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     *
     * @param msg The error message.
     */
    public DukeException(String msg) {
        super(msg);
    }

}
