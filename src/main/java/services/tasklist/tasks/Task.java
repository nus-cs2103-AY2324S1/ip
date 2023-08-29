package services.tasklist.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String checkBox;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.checkBox = "[ ]";
    }

    public void setDone() {
        this.isDone = true;
        this.checkBox = "[X]";
    }

    public void setUndone() {
        this.isDone = false;
        this.checkBox = "[ ]";
    }

    public abstract String encode();

    @Override
    public String toString() {
        return checkBox + " " + this.description;
    }
}
