public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
