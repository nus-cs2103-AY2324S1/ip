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
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof ToDo) {
            ToDo todo = (ToDo) other;
            return this.description.equalsIgnoreCase(todo.description);
        } else {
            return false;
        }
    }
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
