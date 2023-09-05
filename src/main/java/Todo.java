/**
 * Represents a to-do task with a description.
 */
public class Todo extends Task {

    /**
     * Initializes a new to-do task with the given description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
        this.isDone = false;
    }

    /**
     * Returns a formatted string representation of the to-do task, including its type and description.
     *
     * @return A formatted string representing the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the to-do task to be saved into a file.
     *
     * @return A string representing the to-do task in a file format.
     */
    @Override
    public String getDescription() {
        return "T" + super.getDescription();
    }
}
