public class Task {
    protected String description;
    protected boolean isDone;
    protected static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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
