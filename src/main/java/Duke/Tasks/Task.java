package Duke.Tasks;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    public final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public String description;
    public boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    private String getStatusIcon() {
        return (isMarked ? "[X]" : "[ ]");
    }

    abstract public String writeFormat();

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
