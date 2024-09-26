package anya.task;

/**
 * Represents a todo task in the Anya application.
 * A todo task is a simple task with a description.
 */
public class Todo extends Task {

    /**
     * Constructs a new `Todo` instance with the specified description.
     *
     * @param description A description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new `Todo` instance with the specified description and done status.
     *
     * @param description A description of the todo task.
     * @param isDone      A boolean indicating whether the task is marked as done.
     */
    public Todo(String description, Boolean isDone) {
        super(description);
        if (isDone) {
            this.markAsDone();
        }
    }

    /**
     * Returns the type of task, which is 'T' for todo.
     *
     * @return The task type.
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Formats the `Todo` object as a string for saving to storage.
     *
     * @return A string representation of the `Todo` object suitable for storage.
     */
    public String formatToSave() {
        return "T" + super.formatToSave();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
