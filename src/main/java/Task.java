public class Task {
    boolean done = false;
    String task;
    public Task(String task) {
        this.task = task;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        return "[" + ((done)?"X":" ") + "] " + task;
    }
}
