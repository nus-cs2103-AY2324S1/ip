package sana;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a todo task object.
     *
     * @param description todo task description.
     * @param isDone indicator of whether the task is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns string representation of todo task.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted todo task string for storage.
     *
     * @return returns formatted todo task string for storage.
     */
    @Override
    public String formatTask() {
        return "T" + super.formatTask();
    }

}
