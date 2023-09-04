package harvard;
/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * The type of the task.
     */
    protected String type = "T";
    /**
     * Constructs a Todo object.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description, "T");
    }
    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */

    @Override
    public String toFileString() {
        return this.type + " | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString();
    }
}
