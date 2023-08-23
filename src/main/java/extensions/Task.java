package extensions;
public class Task {
    protected String text;
    protected boolean isDone;
    public Task(String text) {
        this.text = text;
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsNotDone() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return String.format("[%c] %s", this.isDone ? 'X': ' ', this.text);
    }
}
