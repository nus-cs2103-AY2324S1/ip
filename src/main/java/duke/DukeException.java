package duke;

/**
 * Catches checked exceptions during the execution of Duke.
 */
public class DukeException extends Exception {
    public DukeException(String errMsg) {
        super(errMsg);
    }
}
