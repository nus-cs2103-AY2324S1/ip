public class Task {
    private String name;
    private boolean done = false;

    // Constructor for Task.
    public Task (String name) {
        this.name = name;
    }

    // Check if the task is done.
    public boolean isDone() {
        return done;
    }

    // Get string representation of the task.
    public String toString() {
        String checkbox = done ? "[X] " : "[ ] ";
        return checkbox + name;
    }

    // Mark task as done.
    public void markDone() {
        done = true;
    }

    // Mark task as undone.
    public void markUndone() {
        done = false;
    }
}
