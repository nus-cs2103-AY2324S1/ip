package duke.exceptions;

/** Encapsulates exceptions due to wrong input. */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     * @param message Exception message.
     */
    public DukeException(String message) {
        super(message);
    }
}
