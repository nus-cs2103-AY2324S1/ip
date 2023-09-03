package duke;

/**
 * Represents exceptions unique to the application.
 */
public class DukeException extends Exception {

    /**
     * Initialises a new DukeException object with specified message.
     *
     * @param message String that gives more details on the error.
     */
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
