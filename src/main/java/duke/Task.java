package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static int total = 0;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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

    public void remove() {
        Task.total -= 1;
    }

    public String toSaveString() {
        return "";
    }
}
