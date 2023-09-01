package duke.task;

/**
 * Represents a duke.task.Task to be done.
 */
public class Todo extends Task {

    /**
     * Constructor for a duke.task.Todo instance.
     *
     * @param description The description of the underlying task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Provides the string representation of the duke.task.Todo instance.
     *
     * @return A string with the relevant information of the duke.task.Todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Get a formatted string of the duke.task.Todo task to add to the save file.
     *
     * @return A formatted string with the relevant information for the save file.
     */
    @Override
    public String getSaveString() {
        return String.format("T | %d | %s",
                this.isDone ? 1 : 0,
                this.description);
    }
}
