/**
 * A class which inherits from Task class.
 * Represents a task without deadline or start time.
 */
public class Todo extends Task {

    /**
     * Initialises with the given description.
     *
     * @param description The name of this Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this Todo task.
     *
     * @return Returns a string describing this task.
     */
    @Override
    public String toString() {

        return "[T]" + super.toString();
    }

}
