import java.io.Serializable;

public abstract class Task implements Serializable {
    private String task;
    private boolean isDone;
    private String icon = "[ ]";

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
        this.icon = "[X]";
    }

    @Override
    public String toString() {
        return this.icon + " " + this.task;
    }
}
