package duke.tasks;

/**
 * Represents a generic task with a specific type, description, and completion status.
 *
 * <p> The Task class is a fundamental representation of a task that can be of various types
 * such as TODO, DEADLINE, or EVENT.
 * Each task has a type, a description, and a flag that indicates whether it has been completed. </p>
 */
public class Task {
    private TaskType type;
    private String description;
    private boolean done;

    /**
     * Constructs a new Task instance with the specified type and description.
     *
     * @param type        The type of the task, which can be one of the values in {@link TaskType}.
     * @param description A brief description of the task.
     */
    public Task(TaskType type, String description) {
        this.description = description;
        this.type = type;
        this.done = false; // Default value for a new task
    }

    /**
     * Provides a string representation of the task, which includes its type, completion status, and description.
     *
     * @return A formatted string indicating the task's type, whether it's done, and its description.
     */
    @Override
    public String toString() {
        String printType;
        switch (type) {
        case TODO:
            printType = "T";
            break;
        case EVENT:
            printType = "E";
            break;
        case DEADLINE:
            printType = "D";
            break;
        default:
            printType = "";
            break;
        }
        String indicator = done ? "X" : " ";
        return "[" + printType + "][" + indicator + "] " + description;
    }

    /**
     * Sets the task's completion status to 'done'.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Resets the task's completion status to 'not done'.
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Transforms the task's data into a specific format, useful for data storage or other representations.
     *
     * @return A string that represents the task's type, completion status, and description in a unique format.
     */
    public String transformFormat() {
        String printType;
        switch (type) {
        case TODO:
            printType = "T";
            break;
        case EVENT:
            printType = "E";
            break;
        case DEADLINE:
            printType = "D";
            break;
        default:
            printType = "";
            break;
        }
        return printType + " | " + this.done + " | " + this.description;
    }

    /**
     * Returns the type of this task.
     *
     * @return A {@code TaskType} representing the type of the task.
     */
    public TaskType getTaskType() {
        return this.type;
    }

    /**
     * Returns the description of this task.
     *
     * @return A {@code String} representing the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the completion of this task.
     *
     * @return A {@code boolean} representing the completion of the task.
     */
    public boolean isDone() {
        return this.done;
    }
}
