public class Task {
    private boolean done;
    private String name;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    void mark() {
        done = true;
    }

    void unmark() {
        done = false;
    }
    @Override
    public String toString() {
        if (done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
