package task;

/**
 * Represents a Task that just has a description.
 */
public class ToDo extends Task {
    /**
     * Constucts a ToDo with a description.
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of the ToDo.
     * @return a String representation
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a String representation of the ToDo for the .txt file.
     * @return a String representation
     */
    @Override
    public String toFileString() {
        int bool = this.isDone ? 1 : 0;
        return "T | " + bool + " | " + this.description + "\n";
    }
}
