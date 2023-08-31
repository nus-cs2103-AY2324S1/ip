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

    public boolean getDoneStatus() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatus() {
        return this.isDone ? "[X]" : "[ ]";
    }
    public abstract String toText();

    public String toString() {
        return this.getStatus() + " " + this.description;
    }
}
