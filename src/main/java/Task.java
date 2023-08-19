public class Task {
    private boolean isDone;
    private String taskName;

    public Task(String taskName) {
        isDone = false;
        this.taskName = taskName;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unMarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
