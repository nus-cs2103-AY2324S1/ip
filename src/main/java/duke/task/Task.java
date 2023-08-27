package duke.task;

public class Task {
    // Fields
    private String description;
    private boolean isDone;

    // Constructor for duke.task.Task
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format("[X] %s", description);
        } else {
            return String.format("[ ] %s", description);
        }
    }
}
