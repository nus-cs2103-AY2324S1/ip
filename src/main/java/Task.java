public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X]" + description;
        }
        return "[ ]" + description;
    }
}
