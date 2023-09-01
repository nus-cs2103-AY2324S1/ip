package task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getType() {
        return " ";
    }

    public String getDescription() {
        return this.description;
    }

    public String getDetails() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
