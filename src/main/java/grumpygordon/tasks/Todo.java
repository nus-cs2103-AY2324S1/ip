package grumpygordon.tasks;

/**
 * Represents a grumpygordon.tasks.Todo object.
 */
public class Todo extends Task {

    /**
     * Constructor to create a grumpygordon.tasks.Todo object.
     * @param description Description of task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a String representation of a grumpygordon.tasks.Todo object.
     * @return String representation of a grumpygordon.tasks.Todo object.
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + super.toString();
    }
}