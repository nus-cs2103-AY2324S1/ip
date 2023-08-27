public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() { //change to seperate it with the description
        return "[" + (isDone ? "X" : " ") + "]"; // mark done task with X
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    public void markAsDone(int task) {
        this.isDone = true;
    }

    public void markAsNotDone(int task) {
        this.isDone = false;
    }

    public abstract String fileString();
}
