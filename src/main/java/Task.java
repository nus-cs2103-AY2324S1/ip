public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone(boolean status) {
        this.isDone = status;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    public abstract String toSave();
}
