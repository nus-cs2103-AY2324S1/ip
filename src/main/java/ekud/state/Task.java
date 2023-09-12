package ekud.state;

import ekud.util.DateTime;

/**
 * Represents a task to be completed.
 */
public abstract class Task {
    private final String title;
    private boolean isDone;

    protected Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    /**
     * Returns the title of the task.
     * 
     * @return The title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns whether the task has been completed.
     * 
     * @return Whether the task is complete.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the string representation of this task, to be displayed to the user.
     * 
     * @return The string representation.
     */
    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return "[" + mark + "] " + title;
    }

    /**
     * Returns the hash code of this task to be used in a set.
     * The hash code ignores whether the task is done, because when looking for
     * duplicate tasks we don't care if either task has been done or not done.
     * 
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof Task)) {
            return false;
        }
        return title.equals(((Task) other).title);
    }
}
