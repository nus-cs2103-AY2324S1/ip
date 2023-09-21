package duke.task;

/**
 * Represents a to-do task with a name and completion status.
 */
public class ToDo extends Task {

    /**
     * Constructs a new to-do task with the specified name and completion status.
     *
     * @param name   The name of the to-do task.
     * @param isDone The completion status of the to-do task.
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns a string representation of the `ToDo` task, including its type and
     * name.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the `ToDo` task in a format suitable for
     * data storage.
     *
     * @return A string representation of the task for data storage.
     */
    @Override
    public String toDataString() {
        return "T|" + super.toDataString();
    }
}
