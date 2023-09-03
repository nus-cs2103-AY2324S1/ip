package anya.task;

import java.time.LocalDateTime;

/**
 * Represents a generic task in the Anya application.
 * A task has a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new `Task` instance with the specified description and initializes it as not done.
     *
     * @param description A description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done anya.task with X
    }

    public String getStatusNumber() {
        return (isDone ? "1" : "0"); // mark done anya.task with 1
    }

    public String getType() {
        return "";
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String formatToSave() {
        return " | " + getStatusNumber() + " | " + this.description;
    }

    public LocalDateTime convertStringToDate(String dateString) {
        return LocalDateTime.parse(dateString);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
