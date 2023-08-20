public class Task {
    protected String task;
    protected boolean toBeDone;

    public Task(String task) {
        this.task = task;
        this.toBeDone = true;
    }

    public void mark() {
        toBeDone = false;
    }

    public void unmark() {
        toBeDone = true;
    }

    public String getStatusIcon() {
        return (toBeDone ? "[ ]" : "[X]");
    }

    public String getTask() {
        return task;
    }
}
