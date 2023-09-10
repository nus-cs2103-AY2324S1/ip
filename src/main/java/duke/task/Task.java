package duke.task;

/**
 * Represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task.
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task to be done.
     * @return Response of task being marked done.
     */
    public String setDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n"
                + "%s\n" + "----------\n", this);
    }

    /**
     * Marks the task to be undone.
     * @return Response of task being marked undone.
     */
    public String unsetDone() {
        this.isDone = false;
        return String.format("OK, I've marked this task as not done yet:\n"
                + "%s\n" + "----------\n", this);
    }

    /**
     * Checks if inputted keyword is found in task description.
     * @param keyword Keyword to find in description.
     * @return True if found.
     */
    public boolean match(String keyword) {
        assert (description != null) : "Task description cannot be null.";
        return description.contains(keyword);
    }

    @Override
    public String toString() {
        assert (description != null) : "Task description cannot be null.";
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
