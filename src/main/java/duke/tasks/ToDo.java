package duke.tasks;

/**
 * A Task without any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Constructor for a ToDo task.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of the ToDo task.
     *
     * @return String representation of the ToDo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Exports the ToDo task to a String to be saved.
     *
     * @return String representation of the ToDo task to be saved.
     */
    @Override
    public String export() {
        return String.format("TODO || %s || %s || %s || %s", super.export(), "", "", "");
    }
}
