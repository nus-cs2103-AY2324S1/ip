package grumpygordon.tasks;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructor to create a Todo task.
     * @param description Description of task
     * @param isDone Boolean that represents whether the task is done
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a String representation of a Todo task.
     * @return String representation of a Todo task
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + super.toString();
    }

    /**
     * Returns the format in which a Todo task will be saved.
     * @return String representation of the save format of a Todo task
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + super.toString();
    }
}
