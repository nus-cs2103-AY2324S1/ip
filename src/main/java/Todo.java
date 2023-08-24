/**
 * Represents a Todo object.
 */
public class Todo extends Task {

    /**
     * Constructor to create a Todo object.
     * @param description Description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of a Todo object.
     * @return String representation of a Todo object.
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + super.toString();
    }
}