public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description; // getter
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    public void toggle() {
        this.isDone = !this.isDone;
    }
}
