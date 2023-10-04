package duke.task;

/**
 * The ToDo class is a task without a due date.
 */
public class ToDo extends Task {
    /**
     * The constructor for a ToDo.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the ToDo.
     *
     * @return Returns the string representation of the ToDo.
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
