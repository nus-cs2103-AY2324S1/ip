package noac.task;

/**
 * Base Task class with description on what to do and
 * boolean for whether the task is done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to initialise the task.
     *
     * @param description Description on what the task is.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return 'X' for done task and space for undone task for printing to user.
     *
     * @return The string as described.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Set isDone to true to mark the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Set isDone to false to mark the task as undone.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Gets the description.
     *
     * @return The description.
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Converts the task to string.
     *
     * @return The string.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon()  + "] " + this.description;
    }

    /**
     * Converts to string to save to file.
     *
     * @return The string to be saved.
     */
    public String printToFile() {
        int i = isDone ? 1 : 0;
        return i + "|" + description;
    }
}