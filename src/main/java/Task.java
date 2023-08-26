public class Task {
    private final String name;
    private boolean isDone;

    protected Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    private Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public Task complete() {
        return new Task(this.name, true);
    }

    public Task incomplete() {
        return new Task(this.name, false);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.name + "\n";
        } else {
            return "[ ] " + this.name + "\n";
        }
    }
}
