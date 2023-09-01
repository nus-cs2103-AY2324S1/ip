package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    protected void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toString() {
        return String.format("[ ][%s] %s", getStatusIcon(), description);
    }

    public String toFileString() {
        return String.format("  # %d # %s", (isDone ? 1 : 0), description);
    }
}
