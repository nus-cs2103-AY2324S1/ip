package tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setIsDone() {
        this.isDone = true;
    }

    public void setIsNotDone() {
        this.isDone = false;
    }

    public String getStatus() {
        return this.isDone ? "[X] " : "[ ] ";
    }

    public String toString() {
        return this.getStatus() + this.description;
    }
}
