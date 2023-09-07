package duke;

/**
 * duke.Task represents a task created by a user.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor method for duke.Task.
     * @param description A brief description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Retrieves the task status.
     * @return The task status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    /**
     * Updates the status of the task as done.
     */
    public void updateAsDone() {
        this.isDone = true;
    }
    /**
     * Updates the status of the task as not done.
     * @return Message for the progress of the task.
     */
    public String markAsDone() {
        this.isDone = true;
        return "     Nice! I've marked this task as done:\n"
                + "       " + this.printDesc();
    }
    /**
     * Updates the status of the task as done.
     * @return Message for the progress of the task.
     */
    public String markAsUndone() {
        this.isDone = false;
        return "     OK, I've marked this task as not done yet:\n"
                + "       " + this.printDesc();
    }
    /**
     * Gets the status of the task.
     * @return Message for the status of the task.
     */
    public String printDesc() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    /**
     * Get the message after creating the new task.
     * @return Message for the new creation of the task.
     */
    public String printMessage(int numTask) {
        return "     Got it. I've added this task:\n"
                + "       " + this.printDesc() + "\n"
                + "     Now you have " + (numTask + 1) + " tasks in the list.";
    }
    /**
     * Gets the description and status of the task.
     * @return Message for the description and status of the task.
     */
    public String getDescription() {
        if (this.isDone) {
            return "done~" + this.description;
        } else {
            return "notDone~" + this.description;
        }
    }
    /**
     * Gets the description of the task.
     * @return Message for the description of the task.
     */
    public String getDesc() {
        return this.description;
    }
}
