package duke;

public abstract class Task {
    private boolean isDone;
    private final String taskName;

    public Task(String taskName) {
        this.isDone = false;
        this.taskName = taskName;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", (this.isDone ? 'X' : ' '), this.taskName);
    }

    public abstract String serialize();
}
