package task;

public abstract class Task {

    protected Boolean done;

    public Task(boolean done) {
        this.done = done;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    public String toFileString() {
        return "task";
    }
}
