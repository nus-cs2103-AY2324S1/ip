package duke.task;

/**
 * Represents a todo task in the list of tasks.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the description of the todo task that is written to the data file.
     *
     * @return String representation of todo task stored in the data file.
     */
    @Override
    public String writeFile() {
        return "T | " + super.writeFile();
    }

    /**
     * Return the description of the todo task that is printed to the user.
     *
     * @return String representation of todo task printed to the user.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
