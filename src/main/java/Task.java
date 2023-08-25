/**
 * The Task class encapsulates a real-life task. A basic task has a description and keeps track of
 * whether it is completed or not. It is declared abstract as as task must take one of three forms:
 * ToDo, Deadline or Event.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
       return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
