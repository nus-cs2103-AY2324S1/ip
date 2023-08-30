package friday;

/**
 * Represents a task in the Friday application.
 */
public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns a status icon based on whether the task is done.
     * @return A string representing the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Mark the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }

    /**
     * Checks if keyword is contained in task description.
     * @param keyWord The keyword to check for.
     * @return whether the keyword is in task.
     */
    public boolean containsKeyWord(String keyWord) {
        String lowerCaseName = this.name.toLowerCase();
        return lowerCaseName.contains(keyWord.toLowerCase());
    }
}