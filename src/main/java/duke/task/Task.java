package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toString() {
        return "[" + this.type + "]" + "[" + getStatusIcon() + "] " + description;
    }

    public void changeStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public String toFileString() {
        return this.type + " | " + (isDone ? "1" : "0") + " | " + description;
    }
}