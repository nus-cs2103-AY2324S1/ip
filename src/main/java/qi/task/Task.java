package qi.task;

public class Task {
    protected String task;
    protected boolean done;

    protected Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void mark(boolean status) {
        this.done = status;
    }
}
