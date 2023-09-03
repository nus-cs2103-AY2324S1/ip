package geraldbot.task;

/**
 * Represents a to-do task in the task list.
 */
public class Todo extends Task {

    /**
     * Constructs a to-do task with the specified description and completion status.
     *
     * @param description The description of the to-do task.
     * @param isDone      The completion status of the to-do task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the file format representation of the to-do task.
     *
     * @return The file format representation of the to-do task.
     */
    @Override
    public String fileFormat() {
        return "T " + super.fileFormat();
    }

    /**
     * Returns the string representation of the to-do task.
     *
     * @return The string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
