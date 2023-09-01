package duke.exception;

/**
 * Handles creating To Do event without description.
 */
public class DukeEmptyToDoException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "The description of a todo cannot be empty.";
    }
}
