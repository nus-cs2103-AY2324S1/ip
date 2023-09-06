package tasks;
/**
 * Represents a task created by the user.
 */
public abstract class Task {
    // Every task has a description
    protected String description;
    // Marks whether the task is completed already or not
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void markAsDone() { this.isDone = true; }

    public void markAsNotDone() { this.isDone = false; }

    /**
     * Returns a checked symbol if this task is done.
     * @return char
     */
    public char getDoneSymbol() { return isDone ? 'X': ' '; }

    /**
     * Returns a compact format for saving this Task to the hard disk,
     * which is then easily parsed for future loading of saved tasks.
     * @return String
     */
    abstract public String getSaveFormat();
}
