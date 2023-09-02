public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markTaskAsDone() {
        this.isDone = true;
    }

    public void markTaskAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskType() {
        return "";
    }

    public String toSaveFileFormat() {
        return String.format("%s | %d | %s",
                getTaskType(),
                isDone ? 1 : 0,
                this.description);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] "+ this.description;
    }
}
