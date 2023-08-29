package task;

public abstract class Task {
    private static final String COMPLETE = "[X] ";
    private static final String INCOMPLETE = "[ ] ";
    private final String name;
    private boolean isDone;


    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setComplete() {
        this.isDone = true;
    }

    public void setIncomplete() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return Task.COMPLETE + this.name;
        } else {
            return Task.INCOMPLETE + this.name;
        }
    }
}
