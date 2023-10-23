package URBOI_PACKIN;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    protected String description;
    protected boolean isDone;

    protected LocalDateTime date;

    public String toFileString() {
        return "";
    }
    public String formatDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
    public Task(String description, LocalDateTime date) {
        this.description = description;
        this.isDone = false;
        this.date = date;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // Return a tick or cross symbol cuz im lazy like that, or its easier. idk
    }
    /**
     * Get the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }
    public boolean isDone() {
        return isDone;
    }
    public void markDone() {
        isDone = true;
    }
    public void markNotDone() {
        isDone = false;
    }
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
