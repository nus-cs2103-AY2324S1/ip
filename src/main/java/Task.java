public abstract class Task {
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

    public String data() {
        return this.isDone ? "1 " : "0 " + this.name;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }
}
