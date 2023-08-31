package duke;

/**
 * Represents a general task with a name and completion status.
 */
public class Task {
    protected String taskName;
    protected boolean status;

    /**
     * Initializes a new Task object with the specified task name and default status (not done).
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }

    /**
     * Marks the task as done by setting its status to true.
     */
    public void markDone() {
        this.status = true;
    }

    /**
     * Marks the task as not done by setting its status to false.
     */
    public void unmarkDone() {
        this.status = false;
    }

    /**
     * Retrieves the name of the task.
     *
     * @return The name of the task.
     */

    public String getName() {
        return this.taskName;
    }

    /**
     * Retrieves the type of the task.
     *
     * @return The type of the task. (Override this method in subclasses to provide specific types)
     */
    public String getType() {
        return "No task type";
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is marked as done, false otherwise.
     */
    public boolean isDone() {
        return this.status;
    }

    /**
     * Generates the status representation of the task.
     *
     * @return A string representation of the task's status.
     */
    public String statusString() {
        String statusString;
        if (this.status) {
            statusString = "X";
        } else {
            statusString = " ";
        }
        return "[" + statusString + "]";
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return "Task";
    }

    /**
     * Generates the status and task name representation of the task.
     *
     * @return A string representation of the task's status and name.
     */

    public String statusAndTask() {
        return "[Task]" + statusString() + " " + this.taskName;
    }

    /**
     * Generates a string representation of the task.
     *
     * @return The name of the task.
     */
    @Override
    public String toString() {
        return this.taskName;
    }

}
