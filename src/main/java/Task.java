public class Task {
    private String task;
    private boolean isDone;
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getDoneIcon() {
        return isDone ? "X" : " ";
    }
    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getDoneIcon(), task);
    }
}
