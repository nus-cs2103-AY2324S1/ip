package smolbrain.task;

/**
 * Creates a todo task with description.
 */
public class Todo extends Task {

    /**
     * Creates a todo task.
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of this task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Encodes this task into a string for saving into save file.
     *
     * @return Encoded string representation.
     */
    @Override
    public String encode() {
        return "T" + getStatusNumber() + super.description;
    }

}
