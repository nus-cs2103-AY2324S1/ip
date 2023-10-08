package duke.task;

/**
 * Represent the task in Duke Chat Bot.
 *
 * @author Armando Jovan Kusuma
 */
public class Task {

    private static final String LINE = "____________________________________________________________";
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with a specified description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Checks whether or not the list of tasks contain the specific keyword.
     *
     * @param keyword the keyword to be searched in the list of tasks.
     * @return boolean depending on if the keyword exists.
     */
    public boolean contains(String keyword) {
        return this.description.contains(keyword);
    }
}

