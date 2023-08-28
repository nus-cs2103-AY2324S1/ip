
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void markUndone() {
        this.isDone = false;
    }
    public void markDone() {
        this.isDone = true;
    }
    @Override
    public String toString() {
        String tmp = "[" + this.getStatusIcon() + "]" + " " + this.description;
        return tmp;
    }

}
