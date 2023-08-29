package src.alpha;
// Task Class to handle each individual task that is either marked or unmarked
public abstract class Task {

    // Description/Content of the task itself
    private String description;

    // Boolean to check whether the task is marked
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    // Returns "X" if marked, " " if not
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark task done with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}
