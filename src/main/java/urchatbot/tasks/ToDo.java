package urchatbot.tasks;

/**
 * Represents a Todo task.
 */
public class ToDo extends Task {
    /**
     * Constructs the Todo class.
     *
     * @param description Description of the task.
     * @param isDone If the task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }
    @Override
    public String toFileString() {
        if (this.isDone) {
            return "T | 1 | " + description;
        } else {
            return "T | 0 | " + description;
        }
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
