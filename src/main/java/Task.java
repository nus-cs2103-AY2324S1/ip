public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String mark(boolean isDone) {
        this.isDone = isDone;
        return this.toString();
    }

    public String fileFormat() {
        return String.format("%d%s%s",
                isDone ? 1 : 0, Storage.SEPARATOR, description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            Task task = (Task) o;
            return this.description.equals(task.description) && this.isDone == task.isDone;
        }
        return false;
    }
}
