public class Task {
    private String name;
    private boolean done = false;

    // Constructor for Task
    public Task(String name) {
        this.name = name;
    }

    // Constructor for Task with done status
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    // Checks if the task is done.
    public boolean isDone() {
        return done;
    }

    // Gets string representation of the task
    public String toString() {
        String checkbox = done ? "[X] " : "[ ] ";
        return checkbox + name;
    }

    // Marks task as done
    public void markDone() {
        done = true;
    }

    // Marks task as undone
    public void markUndone() {
        done = false;
    }

    // Gets name of Task
    public String getName() {
        return this.name;
    }
}
