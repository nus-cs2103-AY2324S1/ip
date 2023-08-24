public class Task {
    private String task;
    private boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void mark(boolean status) {
        this.done = status;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.task;
        }
        return "[ ] " + this.task;
    }
}
