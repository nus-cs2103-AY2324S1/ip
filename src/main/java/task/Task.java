package task;

import java.time.format.DateTimeFormatter;

/**
 * Super class for all tasks used in Bocchi
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

    public Task(String name, boolean marked) {
        this.name = name;
        this.isMarked = marked;
        this.taskId = taskCount;
        taskCount++;
    }

    public Task(String name) {
        this(name, false);
    }

    /**
     * Marks the task.
     *
     * @return Marked task
     */
    abstract Task mark();

    /**
     * Unmarks the task.
     *
     * @return Unmarked task
     */
    abstract Task unmark();

    /**
     * Returns the name of the task
     *
     * @return Name of task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Creates the string to be saved into the save file
     *
     * @return String to be saved
     */
    public String saveTask() {
        return String.format("%s | %s", this.isMarked ? "1" : "0", this.name);
    }

    /**
     * Checks if this task has a conflict with another task.
     *
     * @param t The task to check for conflicts with.
     * @return true if there is a conflict, false otherwise.
     */

    public boolean hasConflictWith(Task t) {
        return false;
    }


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
     * Returns the name of task to be done.
     *
     * @return Name of task to be done
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isMarked ? "X" : " ", this.name);
    }
}
