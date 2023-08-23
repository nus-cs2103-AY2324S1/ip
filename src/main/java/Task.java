public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String indicator = done ? "X" : " ";
        return "[" + indicator + "] " + description;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }
}
