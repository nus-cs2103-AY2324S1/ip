package duke.tasks;

/**
 * Represents a todo task.
 * A <code>Todo</code> object corresponds to a todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a <code>Todo</code> object.
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts a todo task to file format.
     * @return The description of the todo task.
     */
    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }

    /**
     * Converts a todo task from file format.
     * @return The description of the todo task.
     */
    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        Todo todo = new Todo(parts[2]);
        if (parts[1].equals("1")) {
            todo.markDone();
        }
        return todo;
    }

    /**
     * Returns the string representation of a todo task.
     * @return The string representation of a todo task.
     */
    @Override
    public String toString() {
        return "[T][" + (getIsDone() ? "X" : " ") + "] " + getDescription();
    }
}
