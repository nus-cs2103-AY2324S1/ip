package duke;

/**
 * The `ToDo` class represents a specific type of task, namely a to-do task,
 * which is a task without a specific date or time associated with it.
 * It extends the `Task` class and includes methods to mark the task as done
 * and convert the task to a string format for display and saving.
 */
public class ToDo extends Task {

    /**
     * A boolean flag indicating whether the to-do task is marked as done.
     */
    protected boolean isDone = false;

    /**
     * Constructs a new `ToDo` task with the given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new `ToDo` task with the given description and done status.
     *
     * @param description The description of the to-do task.
     * @param isDone      A boolean indicating whether the task is marked as done.
     */
    public ToDo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the to-do task, including its type indicator '[T]'
     * and the description.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the to-do task in a format suitable for saving to a file.
     * The format is 'T | description'.
     *
     * @return A string representation of the to-do task for saving.
     */
    @Override
    public String toSaveString() {
        String divider = " | ";
        return "T" + divider + super.toSaveString();
    }
}