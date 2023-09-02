package tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
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

    public String toStringForFile() {
        String status = isDone ? "1" : "0";
        return status + " | " + description;
    }
}
