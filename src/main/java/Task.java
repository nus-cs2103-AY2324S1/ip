public class Task {
    protected String name;
    protected boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String showStatus() {
        return (isDone ? "X" : " ");
    }

    public String showName() {
        return this.name;
    }
}
