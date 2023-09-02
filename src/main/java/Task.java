public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() { // encapsulation principle
        return (isDone ? "1" : "0") + " | " + description;
    }

    public void markTask(boolean mark) {
        isDone = (mark) ? true : false;
    }
}
