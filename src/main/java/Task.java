/**
 * Represents a task with a description and status,isDone
 * Task can be marked as done or not done.
 */
public class Task {
    protected String description; // description of the task
    protected boolean isDone; // isDone keep track the status of task whether it is done or not

    /**
     * Construct a new task with the given description. The task is initialised as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status of the task based on the variable isDone.
     *
     * @return An "X" if the task is done, otherwise a space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    // Marks the task as done by setting its status (isDone) to true
    public void markAsDone() {
        this.isDone = true;
    }

    // Marks the task as not done by setting its status (isDone) to false
    public void markAsNotDone() {
        this.isDone = false;
    }
}