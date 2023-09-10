package duke.exception;

/**
 * Handles invalid mark / unmark argument entered.
 */

public class DukeInvalidMarkUnmarkArgumentException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "Wrong argument entered for mark / unmark.";
    }
}
