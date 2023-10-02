package moss;

/**
 * Represents a to-do task that can be managed in the task management application.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo object with the given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo object.
     *
     * @return A string containing task type and description.
     */
    @Override
    public String toString() {
        return "T | " + super.toString();
    }

    /**
     * Returns a string representation of the ToDo object.
     *
     * @param x A placeholder parameter to differentiate this method signature.
     * @return A string containing task type and description.
     */
    @Override
    public String toString(String x) {
        return "T | " + super.toString();
    }
}

