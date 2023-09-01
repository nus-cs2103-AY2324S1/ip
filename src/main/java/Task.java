public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    public String format() {
        if (this instanceof Todo) {
            return String.format("T | %s | %s", isDone ? "1" : "0", getDescription());
        } else if (this instanceof Deadline) {
            Deadline deadline = (Deadline) this;
            return String.format("D | %s | %s | %s", isDone ? "1" : "0", getDescription(), deadline.getBy());
        } else if (this instanceof Event) {
            Event event = (Event) this;
            return String.format("E | %s | %s | %s - %s", isDone ? "1" : "0", getDescription(), event.getFrom(), event.getTo());
        }
        return "";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}