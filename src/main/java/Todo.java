/**
 * Represents a task of type todo
 *
 * @author Celestine
 */
public class Todo extends Task {

    /**
     * A constructor for a task of type todo
     *
     * @param description the task details
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFileFormat() {
        return "T | " + this.isDone + " | " + this.description;
    }
}
