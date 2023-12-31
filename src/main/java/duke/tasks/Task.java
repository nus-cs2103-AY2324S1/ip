package duke.tasks;

/**
 * Represents an abstract task in the task management system.
 * This class provides methods to manage and query the state of the task.
 *
 * <p>Subclasses should implement the {@link #saveString()} method to provide
 * their own specific string representations for saving.</p>
 */
public abstract class Task {

    /** Flag that indicates a task is done. */
    private static final String DONE_FLAG = "[X] ";

    /** Flag that indicates a task is not done. */
    private static final String UNDONE_FLAG = "[ ] ";

    /** Description of the task. */
    private final String description;

    /** Indicates whether the task is done. */
    private boolean isDone;

    /** Type of the task (e.g., TODO, DEADLINE, EVENT). */
    private final TaskType type;

    /**
     * Initializes a new task with a given description and type.
     *
     * @param description The task's description.
     * @param type        The task's type.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        isDone = false;
        this.type = type;
    }

    /**
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return {@code true} if the task is marked as done, {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * @return A flag indicating whether the task is done or not.
     */
    public String getFlag() {
        return this.isDone() ? DONE_FLAG : UNDONE_FLAG;
    }

    /**
     * Returns a string representation of the task suitable for saving to persistent storage.
     * Subclasses should provide their own implementation.
     *
     * @return A string representation of the task.
     */
    public abstract String saveString();

    /**
     * Checks if the task's description contains a given pattern.
     *
     * @param pattern The pattern to search for.
     * @return {@code true} if the description contains the pattern (case-insensitive), {@code false} otherwise.
     */
    public boolean contains(String pattern) {
        if (pattern == null || this.description == null) {
            return false;
        }

        int patternLength = pattern.length();

        for (int i = 0; i <= this.description.length() - patternLength; i++) {
            if (this.description.regionMatches(true, i, pattern, 0, patternLength)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return (isDone() ? DONE_FLAG : UNDONE_FLAG) + getDescription();
    }
}
