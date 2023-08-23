/**
 * Represents a Task to be done.
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo instance.
     *
     * @param description The description of the underlying task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Provides the string representation of the Todo instance.
     * @return A string with the relevant information of the Todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
