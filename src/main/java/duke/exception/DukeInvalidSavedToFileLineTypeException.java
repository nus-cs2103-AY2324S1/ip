package duke.exception;

/**
 * Handles unknown command entered.
 */
public class DukeInvalidSavedToFileLineTypeException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "Wrong task type saved in disc.";
    }
}

