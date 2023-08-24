public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String get() {
        return description; // mark done task with X
    }

    public void set() {
        this.isDone = true; // mark done task with X
    }

    public void unset() {
        this.isDone = false; // mark done task with X
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.get();
    }

    //...
}