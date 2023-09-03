package bareum;

public class Task {
    String description;
    boolean isDone;

    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toSavedString() {
        return this.description;
    }
}
