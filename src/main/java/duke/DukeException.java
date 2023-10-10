package duke;

/**
 * Class to define a new exception that will be
 * thrown by the program in certain cases.
 */
public class DukeException extends Exception {

    /**
     * Initialises a DukeException with the provided message.
     *
     * @param message message to be shown when the exception is caught and read.
     */
    public DukeException(String message) {
        super(message);
    }
}
