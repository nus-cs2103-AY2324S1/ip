public class Task {

    private boolean isDone;
    private String name;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markDone() {
        if (!this.isDone) {
            this.isDone = true;
        }
    }

    public void markUndone() {
        if (this.isDone) {
            this.isDone = false;
        }
    }

    @Override
    public String toString() {
        return this.isDone ? "[X] " + name : "[ ] " + name;
    }

}
