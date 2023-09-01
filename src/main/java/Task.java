public class Task {
    // abstraction: information-hiding principle
    protected String description;
    protected boolean isDone;

    public Task(String description) { // constructor
        this.description = description;
        this.isDone = false;
    }

    public String toString() { // encapsulation principle
        return (isDone ? "1" : "0") + " |" + description;
    }

    public void markTask(boolean mark) { // tell-don't-ask principle
        isDone = (mark) ? true : false;
    }
}
