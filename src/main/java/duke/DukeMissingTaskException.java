package duke;

/**
 * Represents the different commands that the user can input.
 */
public class DukeMissingTaskException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " No such task exists.";
    }
}
