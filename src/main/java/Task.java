public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to create a Task object.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /** Method to mark task as done. */
    public void markAsDone() {
        isDone = true;
    }

    /** Method to mark task as not done. */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Method to get the status icon of the task based on whether the task is done.
     *
     * @return The status icon of the task.
     *         Returns X if task is done,
     *         else returns an empty space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a boolean representing whether the task is due or an event starts within a week.
     *
     * @return true if task is due / starts within a week, false otherwise
     */
    public boolean isWithinAWeek() {
        return false;
    }

    /**
     * Method to get the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%1s] %2s", getStatusIcon(), description);
    }

    /**
     * Returns task information in format for saving.
     * Format is [1 if completed, 0 if not completed] | [task description]
     *
     * @return Task information in format for saving
     */
    public String getInformationForSaving() {
        return String.format("%1s | %2s", isDone ? "1" : "0", description);
    }
}
