package Task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false; // Initially, the task is not done
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}

