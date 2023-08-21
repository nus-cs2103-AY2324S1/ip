public abstract class Task {

    private String description;
    private boolean isDone;

    public Task(String input) {
        this.description = input;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (this.isDone ? "[X]" : "[ ]") + " " + this.description;
    }
}
