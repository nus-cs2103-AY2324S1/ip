package duke;

/**
 * Represents a task of type todo
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

    /**
     * Return the string representation of the task.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Return the string representation of the task in file format.
     *
     * @return the string representation in file format
     */
    @Override
    public String toFileFormat() {
        return "T | " + this.isDone + " | " + this.description;
    }
}
