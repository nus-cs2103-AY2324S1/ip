package duke.tasks;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo instance with the given description and sets completion status to false.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a ToDo instance with the given description and completion status.
     *
     * @param description The description of the to-do task.
     * @param isDone The completion status of the to-do task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Converts the to-do task to its string representation.
     *
     * @return The string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the to-do task to its file format representation.
     *
     * @return The file format representation of the to-do task.
     */
    public String toFile() {
        return "T" + super.toFile();
    }
}
