/**
 * Represents a Todo object.
 */
public class Todo extends Task {

    /**
     * Constructor to create a Todo object.
     * @param description Description of task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a String representation of a Todo object.
     * @return String representation of a Todo object.
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