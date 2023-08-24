public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
    }

    public void markTask(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        char marked = '\u2717';
        return "[" + (done ? marked : " ") + "] " + this.description;
    }
}
