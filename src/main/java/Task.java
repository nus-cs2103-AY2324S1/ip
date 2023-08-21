public class Task {
    private boolean done;
    private final String taskName;

    public Task(String taskName) {
        this.done = false;
        this.taskName = taskName;
    }

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", (this.done ? 'X' : ' '), this.taskName);
    }
}
