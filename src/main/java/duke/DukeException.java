package duke;

public class DukeException extends Exception {
    /**
     * Constructor for DukeException
     * @param message error message
     * @param e throwable
     */
    public DukeException(String message, Throwable e) {
        super(message, e);
    }
}
