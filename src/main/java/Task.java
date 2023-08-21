public class Task {
    private final String description;
    private Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
