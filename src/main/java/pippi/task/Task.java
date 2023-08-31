package pippi.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X] " : "[ ] ");
    }
    public String getStatus() {
        return (this.isDone ? "| 1 | " : "| 0 | ");
    }

    public String getDescription() {
        return this.description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }
    public String toMemory() {
        return this.toString();
    };

    @Override
    public String toString() {
        return this.getStatusIcon() + this.getDescription();
    }
}
