package duke.exception;

/**
 * Handles duplicated task entered.
 */
public class DukeDuplicatedTaskException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "Duplicated Task!";
    }
}
