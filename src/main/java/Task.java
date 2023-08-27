public class Task {
    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    protected final String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Toggles task from undone to done and vice versa
     */
    public void toggleTask() {
        this.isDone = !this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : "", this.description);
    }
}
