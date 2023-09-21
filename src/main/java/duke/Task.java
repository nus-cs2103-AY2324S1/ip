package duke;

/**
 * duke.Task represents a task created by a user.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isReminder;

    /**
     * Constructor method for duke.Task.
     * @param description A brief description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.isReminder = false;
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
        return "Yummy honey! I've marked this task as done:\n"
                + this.printDesc();
    }
    /**
     * Updates the status of the task as done.
     * @return Message for the progress of the task.
     */
    public String markAsUndone() {
        this.isDone = false;
        return "OK, I've marked this task as not done:\n"
                + this.printDesc();
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
        return "More things to do? Oh dear :( I've added the task:\n"
                + this.printDesc() + "\n"
                + "Now you have " + (numTask + 1) + " tasks in the list.";
    }
    /**
     * Gets the description and status of the task.
     * @return Message for the description and status of the task.
     */
    public String getDescription() {
        String reminder;
        if (this.isReminder) {
            reminder = "reminder";
        } else {
            reminder = "NA";
        }
        if (this.isDone) {
            return "done~" + reminder + "~" + this.description;
        } else {
            return "notDone~" + reminder + "~" + this.description;
        }
    }
    /**
     * Gets the description of the task.
     * @return Message for the description of the task.
     */
    public String getDesc() {
        return this.description;
    }
    /**
     * Updates the task as a reminder.
     * This task will then be printed out everytime the charbot is first launched.
     * @return Message for the progress of the task.
     */
    public String makeReminder() {
        this.isReminder = true;
        return "OK, I will remind you of this task the next time I see you!\n";
    }
    /**
     * Updates the task as a reminder without printing any messages.
     */
    public void updateReminder() {
        this.isReminder = true;
    }
}
