package duke.task;

/**
 * Represents a to-do task that has a description.
 */
public class ToDo extends Task {
    /**
     * Constructor with description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the duke.task.ToDo object.
     *
     * @return A string representation of the duke.task.ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
