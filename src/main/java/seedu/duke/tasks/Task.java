package seedu.duke.tasks;

import java.time.format.DateTimeFormatter;

/**
 * Task class
 */
public abstract class Task {
    public final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private String description;
    private boolean isMarked;

    /**
     * Task constructor
     *
     * @param description user input
     * @param isMarked is task is marked
     */
    public Task(String description, boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
    }

    private String getStatusIcon() {
        return (isMarked ? "[X]" : "[ ]");
    }

    public abstract String writeFormat();

    public void mark() {
        isMarked = !isMarked;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
