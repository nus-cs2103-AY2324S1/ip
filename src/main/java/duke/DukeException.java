package duke;

/**
 * An exception when Duke receives an invalid input.
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException.
     * @param errorMessage Error message shown to user.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
