public class Task {
    protected String description;
    protected boolean isDone;
    protected static int total = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        Task.total += 1;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description; // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public static int getTotal() {
        return Task.total;
    }
}
