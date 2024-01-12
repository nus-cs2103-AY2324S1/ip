package duke.task;

/**
 * The abstract base class for tasks.
 */
public abstract class Task implements Comparable<Task> {
    private String description;
    private boolean isDone = false;

    /**
     * Constructor for task.
     *
     * @param description A String describing the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the task.
     *
     * @return String description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the Task.
     *
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        String msg = "[" + (isDone ? "X" : " ") + "]";
        msg = msg + " " + this.description;
        return msg;
    }

    /**
     * Compare two tasks based on their type (Todo, Deadline, Event).
     *
     * @param otherTask The other task to compare to.
     * @return Negative value if this task is "smaller" than the other task,
     *         positive value if this task is "larger" than the other task,
     *         or zero if they are of the same type.
     */
    @Override
    public int compareTo(Task otherTask) {
        if (this instanceof Todo) {
            if (otherTask instanceof Todo) {
                return 0;
            } else {
                return -1;
            }
        } else if (this instanceof Deadline) {
            if (otherTask instanceof Todo) {
                return 1;
            } else if (otherTask instanceof Deadline) {
                return 0;
            } else {
                return -1;
            }
        } else if (this instanceof Event) {
            if (otherTask instanceof Event) {
                return 0;
            } else {
                return 1;
            }
        }
        return 0;
    }
}
