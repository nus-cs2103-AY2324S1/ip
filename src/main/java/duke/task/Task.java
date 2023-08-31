package duke.task;

/**
 * The Task class represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the icon representing the completion status of the task.
     *
     * @return "X" if the task is done, " " if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone by setting its completion status to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, containing its
     * completion status and description.
     *
     * @return String representation of task status and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string representation of the task when saved in a hard disk file.
     *
     * @return String representation of task status and description for hard disk file.
     */
    public String fileDescription() {
        if (this.isDone) {
            return " | 0 | " + this.description;
        } else {
            return " | 1 | " + this.description;
        }
    }
}
