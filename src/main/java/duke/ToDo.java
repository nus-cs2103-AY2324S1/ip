package duke;

/**
 * Encapsulates todos which are Tasks.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a String representation for the task for output.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a String representation of the task for storage.
     */
    @Override
    public String toTxt() {
        return "T | " + super.toTxt();
    }
}
