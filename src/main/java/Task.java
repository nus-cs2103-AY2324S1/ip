public class Task {

    private String name;

    private boolean done;

    public Task(String task) {
        name = task;
        done = false;
    }

    public String taskName() {
        return name;
    }

    public void mark() {
        done = true;
    }

    public void unmark() {
        done = false;
    }

    public boolean isDone() {
        return done;
    }

    public String status() {
        return done ? "[X]" : "[ ]";
    }
}
