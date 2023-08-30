package duke.task;

public abstract class Task {
    protected boolean done;
    protected String name;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void mark() {
        done = true;
    }

    public void unmark() {
        done = false;
    }

    public abstract String stringifyTask();

    @Override
    public String toString() {
        if (done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
