public class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String toString() { // encapsulation principle
        return (isDone ? "1" : "0") + " | " + description;
    }

    public void markTask(boolean mark) {
        isDone = (mark) ? true : false;
    }
}
