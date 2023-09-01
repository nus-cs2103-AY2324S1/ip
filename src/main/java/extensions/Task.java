package extensions;
/**
 * Represents a task created by the user.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsNotDone() {
        this.isDone = false;
    }
    public char getDoneSymbol() { return isDone ? 'X': ' '; }

    abstract public String getSaveFormat();
}
