public class Task {
    private String name;
    private boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }
}
