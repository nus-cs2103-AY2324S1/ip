package duke.tasks;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String name;
    private boolean isDone;

    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }

    public String toFileString() {
        return this.getStatusIcon() + " | " + this.name;
    }

    public static DateTimeFormatter getDateOutputFormat() {
        return OUTPUT_FORMAT;
    }

    public String toString() {
        return name;
    }
}
