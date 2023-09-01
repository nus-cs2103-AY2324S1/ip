package duke.tasks;

import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns whether the task description contains a specified keyword.
     *
     * @param s The keyword to look for.
     * @return True if the description contains the specified keyword.
     */
    public boolean hasKeyword(String s) {
        return description.contains(s);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
