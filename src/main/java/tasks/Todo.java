package tasks;

/**
 * The class representing a Todo task.
 *
 * @author Gallen Ong
 */
public class Todo extends Task {

    /**
     * Initialises a Todo task with a description.
     *
     * @param description The task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initialises a Todo task by retrieving from existing file.
     *
     * @param status The task status retrieved.
     * @param description The task description retrieved.
     */
    public Todo(String status, String description) {
        super(description);
        if (status.equals("1")) {
            this.isDone = true;
        }
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return The Todo task in string format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo task to be added to a file.
     *
     * @return The Todo task in string format, specific for file operations.
     */
    @Override
    public String toStringForFile() {
        return "T | " + super.toStringForFile();
    }
}
