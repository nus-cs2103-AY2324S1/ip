package duke.task;

public abstract class Task {
    protected String message;
    protected boolean isDone;

    public Task(String message) {
        this.message = message;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public String getStatusNumber() {
        return isDone ? "1" : "0";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public abstract String toSaveFormatString();
    public abstract String toString();
}