package duke;

public class Todo extends Task {
    /**
     * Constructs a Todo task.
     *
     * @param description  The description of the todo task.
     */
    public Todo (String description) {
        super(description);
    }

    /**
     * Returns a tring representation of the Todo task.
     *
     * @return  A string representation of the Todo task for the data storage.
     */
    @Override
    public String toDataString() {
        return "TODO | " + super.toDataString();
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return  A string representation of the Todo task for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
