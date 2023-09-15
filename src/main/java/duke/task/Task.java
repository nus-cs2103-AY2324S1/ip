package duke.task;

public class Task {
    private String task;
    private boolean markedStatus;
    private boolean reminderStatus = false;

    /**
     * The Task class represents a basic task with a description and completion status.
     *
     * @param task
     * @param done
     */
    public Task(String task, boolean reminderStatus, boolean done) {
        this.task = task;
        this.markedStatus = done;
        this.reminderStatus = reminderStatus;
    }

    /**
     * Returns a string representation of the Task, including the completion status.
     *
     * @return A formatted string representing the task and its completion status.
     */
    public String toString() {
        String message;
        if (this.markedStatus) {
            message = "[X] " + this.task;
        } else {
            message = "[ ] " + this.task;
        }
        return message;
    }

    /**
     * Returns a string representation of the Task for saving to a file.
     *
     * @return The formatted string suitable for saving to file.
     */
    public String getTaskFileString() {
        String markString = markedStatus ? "1" : "0";
        String reminderString = reminderStatus ? "1" : "0";
        String fileString = markString + " , "
                + reminderString + " , "
                + this.task;
        return fileString;
    }

    /**
     * Marks the task as completed.
     *
     * @return The updated completion status.
     */
    public boolean markTask() {
        this.markedStatus = true;
        return this.markedStatus;
    }

    /**
     * Marks the task as not completed.
     *
     * @return The updated completion status.
     */
    public boolean unmarkTask() {
        this.markedStatus = false;
        return this.markedStatus;
    }

    /**
     * Check if task description contains the keyword.
     *
     * @return true if the task description contains the keyword.
     */
    public boolean contains(String keyword) {
        return this.task.contains(keyword);
    }

    public boolean isMarked() {
        return this.markedStatus;
    }

    public boolean isReminder() {
        return this.reminderStatus;
    }

    public void setReminder() {
        this.reminderStatus = true;
    }
}
