package duke.exception;

/**
 * Handles invalid mark / unmark argument entered.
 */

public class DukeInvalidMarkUnmarkArgument extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "Wrong argument entered for mark / unmark.";
    }
}
