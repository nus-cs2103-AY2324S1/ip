package duke.task;

/**
 * Represent the task in Duke Chat Bot.
 *
 * @author Armando Jovan Kusuma
 */
public class Task {
    protected String description;
    private static final String LINE = "____________________________________________________________";
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

}

