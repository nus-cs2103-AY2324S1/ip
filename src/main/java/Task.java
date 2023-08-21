public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void toggleCompletion() {
        this.isDone = !this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        String output = "[" + this.getStatusIcon() + "] " + this.description;
        return output;
    }
}