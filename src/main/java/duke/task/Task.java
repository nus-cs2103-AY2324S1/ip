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
     * @param d Description of the task.
     */
    public Task(String d) {
        description = d;
        isDone = false;
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
        isDone = true;
    }

    /**
     * Marks the task as undone by setting its completion status to false.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, containing its
     * completion status and description.
     *
     * @return String representation of task status and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task when saved in a hard disk file.
     *
     * @return String representation of task status and description for hard disk file.
     */
    public String toFileFormat() {
        if (isDone) {
            return " | 0 | " + description;
        } else {
            return " | 1 | " + description;
        }
    }
}
