public class Task {
    protected String title;
    protected Boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatus(), title);
    }
}
