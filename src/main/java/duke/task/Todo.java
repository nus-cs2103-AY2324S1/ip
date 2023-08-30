package duke.task;

/**
 * Represents a Todo task, which is a type of Task without a specific date attached to it.
 */
public class Todo extends Task {

    /**
     * A constant defining the type of task as Todo.
     */
    private static final String TYPE = "[T]";

    /**
     * Constructs a new Todo task with the provided name.
     *
     * @param name The name or description of the Todo task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the task in a format suitable for file storage.
     *
     * @return A string representing the task in "T|done_status|name" format.
     */
    @Override
    public String stringifyTask() {
        return String.format("T|%d|%s", this.done ? 1 : 0, this.name);
    }

    /**
     * Returns a string representation of the Todo task, including its type and name.
     *
     * @return A string in the format "[T] TaskName".
     */
    @Override
    public String toString() {
        return TYPE + super.toString();
    }
}
