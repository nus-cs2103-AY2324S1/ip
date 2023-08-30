package duke;

/**
 * This class encapsulates the created exception.
 */
public class DukeException extends RuntimeException {

    /**
     * Constructor for DukeException
     *
     * @param e the message to be used when an exception occur.
     */
    public DukeException(String e) {
        super(e);
    }
}
