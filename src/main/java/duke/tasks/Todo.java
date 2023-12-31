package duke.tasks;

/**
 * Represents a Todo task, a subclass of Task.
 * A Todo task has a description but does not have any specific date/time attached to it.
 *
 * <p>Each Todo task can be stored or printed in specific formats as defined by its constants.</p>
 */
public class Todo extends Task {

    /** Format to print a Todo task to the user. */
    private static final String PRINT_FORMAT = "[T]%s %s";

    /** Format to store a Todo task in storage. */
    private static final String STORE_FORMAT = "[T] | %s | %s";

    /**
     * Initializes a Todo task with the provided description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns the string representation of this Todo task in a format suitable for storage.
     *
     * @return The storage-friendly string representation of this Todo task.
     */
    @Override
    public String saveString() {
        return String.format(STORE_FORMAT, getFlag(), this.getDescription().trim());
    }

    /**
     * Returns the string representation of this Todo task.
     *
     * @return The string representation of this Todo task.
     */
    @Override
    public String toString() {
        return String.format(PRINT_FORMAT, getFlag(), this.getDescription());
    }
}
