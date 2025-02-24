package duke;


/**
 * Task class.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     * @param description name of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the done or not done status icon.
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * toString method of Task class.
     * @return a string
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Generate formatted string to be printed in file.
     * @return a string
     */
    public String stringInFile() {
        return "";
    }
}


