package duke;

/**
 * Abstract class encapsulating Tasks.
 */
public abstract class Task {
    private boolean isDone;
    private final String taskName;

    /**
     * Constructor for a Task.
     * @param taskName Name of task.
     */
    public Task(String taskName) {
        this.isDone = false;
        this.taskName = taskName;
    }

    /**
     * Check if task is marked done.
     * @return true if task is marked done else false.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Mark task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Get name of task.
     * @return Name of task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", (this.isDone ? 'X' : ' '), this.taskName);
    }

    /**
     * Convert data contained by the task into a
     * string format to be stored and deserialized back
     * into a Task object in the future.
     * @return Serialized string representation of Task instance.
     */
    public abstract String serialize();
}
