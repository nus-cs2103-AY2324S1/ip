package pogo.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toFormattedString() {
        return String.format("%s | %s", this.isDone ? "1" : "0", this.description);
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getStatusMessage() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
