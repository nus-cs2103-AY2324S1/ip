abstract class Task {
    protected String description;
    protected boolean isDone;

    abstract public String getDataString();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    protected String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}