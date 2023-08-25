package duke;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the given name.
     *
     * @param name The name of the ToDo task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Generates a formatted string representation of the ToDo task.
     *
     * @return A formatted string representing the ToDo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
