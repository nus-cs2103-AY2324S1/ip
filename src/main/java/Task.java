public class Task {
    private boolean isDone = false;
    private String name;
    public Task(String name) {
        this.name = name;
    }
    private String getName() {
        return this.name;
    }
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + getName();
    }
    public void mark() {
        this.isDone = true;
    }
    public void unmark() {
        this.isDone = false;
    }
}
