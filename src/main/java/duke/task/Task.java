package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String d) {
        description = d;
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String toFileFormat() {
        if (this.isDone) {
            return " | 0 | " + this.description;
        } else {
            return " | 1 | " + this.description;
        }
    }
}
