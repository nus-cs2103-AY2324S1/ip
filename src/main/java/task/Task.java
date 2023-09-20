package task;

import java.time.format.DateTimeFormatter;

/**
 * Superclass for all tasks used in Bocchi.
 */
public abstract class Task {
    protected static final DateTimeFormatter INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    protected static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss");
    private static int taskCount = 0;
    protected final String name;
    private final boolean isMarked;
    private final int taskId;

    /**
     * Constructs a Task with the given name and marked status.
     *
     * @param name   The name of the task.
     * @param marked The marked status of the task.
     */
    public Task(String name, boolean marked) {
        this.name = name;
        this.isMarked = marked;
        this.taskId = taskCount;
        taskCount++;
    }

    /**
     * Constructs an unmarked Task with the given name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this(name, false);
    }

    /**
     * Marks the task.
     *
     * @return A marked task.
     */
    abstract Task mark();

    /**
     * Unmarks the task.
     *
     * @return An unmarked task.
     */
    abstract Task unmark();

    /**
     * Returns the name of the task.
     *
     * @return Name of task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Creates the string representation to be saved into the save file.
     *
     * @return String representation to be saved.
     */
    public String saveTask() {
        return String.format("%s | %s", this.isMarked ? "1" : "0", this.name);
    }

    /**
     * Checks if this task has a conflict with another task.
     *
     * @param t The task to check for conflicts with.
     * @return True if there is a conflict, False otherwise.
     */
    abstract boolean hasConflictWith(Task t);

    /**
     * Checks if two tasks are equal based on their names and task IDs.
     *
     * @param other The object to compare to this task.
     * @return true if the tasks are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Task)) {
            return false;
        }
        Task t = (Task) other;
        return this.name.equals(t.name) && (this.taskId == t.taskId);
    }

    /**
     * Returns a string representation of the task, including its name and marked status.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isMarked ? "X" : " ", this.name);
    }
}
