package duke.task;

/**
 * Represents a general task with a name and completion status.
 * This class serves as an abstract base for more specific types of tasks.
 */
public abstract class Task {

    /**
     * Completion status of the task.
     */
    protected boolean done;

    /**
     * Name or description of the task.
     */
    protected String name;

    /**
     * Constructs a new Task object with a specified name.
     * The task is initially set as not completed.
     *
     * @param name Name or description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        done = true;
    }

    /**
     * Unmarks the task, setting it as not completed.
     */
    public void unmark() {
        done = false;
    }

    /**
     * Converts the task to a formatted string suitable for saving or other specific representations.
     * This method should be overridden by subclasses to provide specific formatting.
     *
     * @return Formatted string representing the task.
     */
    public abstract String stringifyTask();

    /**
     * Returns a string representation of the task with its completion status and name.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        if (done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
