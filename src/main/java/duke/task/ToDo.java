package duke.task;

/**
 * Represents a todo.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String stringToFile() {
        return String.format("T | %s", super.stringToFile());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
