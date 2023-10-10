package friday.item;

/**
 * Represents a task in the Friday application.
 */
public class Task extends Item {
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified name.
     *
     * @param description The name or description of the task.
     */
    public Task(String description) {
        super(description);
        this.isDone = false;
    }

    /**
     * Marks a status icon based on whether the task is done.
     *
     * @return A string representing the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + itemName;
    }

    /**
     * Checks if keyword is contained in task description.
     *
     * @param keyWord The keyword to check for.
     * @return whether the keyword is in task.
     */
    public boolean containsKeyWord(String keyWord) {
        String lowerCaseName = this.itemName.toLowerCase();
        return lowerCaseName.contains(keyWord.toLowerCase());
    }
}
