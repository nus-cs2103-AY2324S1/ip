public abstract class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }

    public String toString() {
        return String.format("[%s] %s", this.done ? "X" : " ", this.name);
    }
}
