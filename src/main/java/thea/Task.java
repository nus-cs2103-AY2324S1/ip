package thea;

/**
 * Represents a task.
 * This class has a description and an indicator of whether the task is done.
 */
public class Task {
    boolean isDone;
    final String taskName;

    /**
     * Constructs a new Task object.
     *
     * @param taskName description of the task.
     */
    public Task(String taskName) {
        this.isDone = false;
        this.taskName = taskName;
    }

    /**
     * Marks itself as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks itself as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Gets description of task.
     *
     * @return description of task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns the task in memory format, which means the format
     * of which the task is saved to the hard disk.
     *
     * @return task in memory format.
     */
    public String toMemoryFormat() {
        return this.toString();
    }

    /**
     * Returns the task in desired string format.
     *
     * @return task in string format.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", (this.isDone ? "X" : " "), this.taskName);
    }
}
