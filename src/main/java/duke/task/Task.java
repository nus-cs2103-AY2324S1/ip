package duke.task;

/**
 * Represents a Task.
 */
public abstract class Task {
    protected boolean isDone;
    protected final String name;

    /**
     * Constructor for Task.
     * @param name Name of Task.
     */
    public Task(String name, boolean isDone) {
        this.isDone = isDone;
        this.name = name;
    }

    /**
     * Mark Task as done.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Mark Task as not done.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns the checkbox icon.
     * @return String representation of checkbox icon.
     */
    public String getCheckBox() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns the string representation of Task.
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return String.format("%s %s", this.getCheckBox(), this.name);
    }

    /**
     * Returns the string representation of Task to store.
     * @return String representation of Task to store.
     */
    public abstract String toStringStore();

    /**
     * Returns true if Task contains keyword.
     * @param keyword Keyword to be searched.
     * @return True if Task contains keyword.
     */
    public boolean containsKeyword(String keyword) {
        return this.name.contains(keyword);
    }
}
