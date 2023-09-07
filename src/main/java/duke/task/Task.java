package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "]";
    }

    public String toFileString() {
        return String.format("%s | %d | %s", this.getTaskType(), this.isDone ? 1 : 0, this.description);
    }

    public String getTaskType() {
        return "";
    }
}
