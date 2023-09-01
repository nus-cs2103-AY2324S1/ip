package duke.exception;

/**
 * Handles unknown command entered.
 */
public class DukeInvalidSavedToFileLineType extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "Wrong task type saved in disc.";
    }
}

