package deterministicparrot;

/**
 * Represents a task of type "To-Do".
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the given description.
     *
     * @param s The description of the ToDo task.
     */
    public ToDo(String s) {
        super(s);
    }

    /**
     * Returns a formatted string representation of the ToDo task.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
