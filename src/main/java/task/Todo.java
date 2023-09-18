package task;

/**
 * Represents a todo task with a description
 */
public class Todo extends Task {

    public static final String TASK_TYPE = "T";

    /**
     * Creates a todo with the given description
     *
     * @param description description of the task
     */
    public Todo(String description) {
        super(description);
        this.taskType = TASK_TYPE;
    }

    /**
     * Formats the task into a string to be written to the storage file
     *
     * @return string to be written to the storage file
     */
    public String formatForFileWriting() {
        return this.getDescription();
    }

    /**
     * Returns a string representation of the task
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
