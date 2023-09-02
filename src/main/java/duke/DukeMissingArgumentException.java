package duke;

/**
 * Represents the different commands that the user can input.
 */
public class DukeMissingArgumentException extends DukeException {
    private final String message;
    public DukeMissingArgumentException(String message) {
        this.message = message;
    }
    public DukeMissingArgumentException() {
        this.message = "There is a missing argument.";
    }
    @Override
    public String toString() {
        return super.toString() + " " + this.message;
    }
}
