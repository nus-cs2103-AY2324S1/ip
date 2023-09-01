package types;

public class Task {
    protected boolean isCompleted;
    protected String description;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getStatus() {
        return (isCompleted ? "[X]" : "[ ]");
    }

    public void unmark() {
        isCompleted = false;
    }

    public void mark() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        return this.getStatus() + " " + description;
    }

}
