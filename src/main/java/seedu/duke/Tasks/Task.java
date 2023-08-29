package seedu.duke.Tasks;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    public final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public String description;
    private boolean isMarked;

    public Task(String description, boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
    }

    private String getStatusIcon() {
        return (isMarked ? "[X]" : "[ ]");
    }

    abstract public String writeFormat();

    public void mark() {
        isMarked = !isMarked;
    }

    public boolean isMarked() {
        return isMarked;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
