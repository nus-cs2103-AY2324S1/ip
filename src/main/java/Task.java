public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected void markDone() {
        this.isDone = true;
    }

    protected void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String statusIcon = isDone ? "[X]" : "[ ]";
        return String.format("%s %s", statusIcon, this.description);
    }
}
