public class Task {
    // TODO: Make this class abstract?
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public String toFileString() {
        return isDone
                ? "X | " + description
                : "0 | " + description;
    }
}
