public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, int isMarked) {
        this.description = description;
        this.isDone = (isMarked == 1) ? true : false;
    }

    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + this.description;
    }
}
