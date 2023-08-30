package main.java;
public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Boolean bool) {
        this.description = description;
        this.isDone = bool;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public String getSaveDescription() {
        return (this.isDone
                ? "| 1 | " + this.description
                : "| 0 | " + this.description);
    }
    public void markUndone() {
        this.isDone = false;
    }
    public void markDone() {
        this.isDone = true;
    }
    @Override
    public String toString() {
        String tmp = "[" + this.getStatusIcon() + "]" + " " + this.description;
        return tmp;
    }

}
