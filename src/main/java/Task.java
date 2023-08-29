import java.time.format.DateTimeFormatter;

abstract public class Task {
    protected static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm");
    protected String description;
    protected boolean isDone;

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    public Task markAsUndone() {
        this.isDone = false;
        return this;
    }

    public abstract String encodeTask();

    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
