package duke.tasks;

/**
 * Represents a task in the Duke application with name and status.
 */
public abstract class Task {

    /** The name of the task. */
    private String name;

    /** The status of the task. */
    private boolean isDone;

    /** The priority of the task. */
    private Priority priority;

    /**
     * Priority levels.
     */
    public enum Priority {
        HIGH(1),
        MEDIUM(2),
        LOW(3);

        private final int value;

        Priority(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Constructs a Task with a given name and priority and set it to be not done.
     *
     * @param name The name of the task.
     */
    public Task(String name, Priority priority) {
        this.name = name;
        this.isDone = false;
        this.priority = priority;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task to indicate that it is not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Sets the priority of the task.
     *
     * @param priority The priority of the task.
     */
    public void setPriority (Priority priority) {
        this.priority = priority;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the formatted description of the task.
     *
     * @return The formatted description of the task.
     */
    public String getDescription() {
        return "[" + this.getStatusIcon() + "] " + this.name + " (Priority: " + this.priority + ")";
    }

    /**
     * Gets the file-formatted description of the task to be written into the data file.
     *
     * @return The file-formatted description of the task.
     */
    public String toFileString() {
        return this.getStatusIcon() + " | " + this.name + " | " + this.priority;
    }

    /**
     * Gets the name of the task.
     *
     * @return The name of the task.
     */
    public String toString() {
        return name;
    }
}
