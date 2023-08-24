package extensions;
/**
 * Represents a task input by the user.
 */
public class Task {
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
    public char getDoneStatus() {
        return isDone ? 'X': ' ';
    }
    @Override
    public String toString() {
        return String.format("[%c] %s",
                this.getDoneStatus(),
                this.description);
    }
}
