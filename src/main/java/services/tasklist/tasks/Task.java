package services.tasklist.tasks;

public class Task {
    private String description;
    private boolean isDone;
    private String checkBox;

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

    @Override
    public String toString() {
        return checkBox + " " + this.description;
    }
}
