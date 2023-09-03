public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]";
    }

    public String toFileString() {
        return String.format("%s | %d | %s", getTaskType(), isDone ? 1 : 0, description);
    }

    public String getTaskType() {
        return ""; // Override in subclasses
    }
}
