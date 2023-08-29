package tasks;

import java.time.format.DateTimeFormatter;

import exceptions.DukeException;

public abstract class Task {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter DATE_FORMAT_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    private String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setStatusIcon(String statusIcon) {
        this.isDone = statusIcon.equals("X");
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public static DateTimeFormatter getDateFormat() {
        return DATE_FORMAT;
    }

    public static DateTimeFormatter getDateFormatOutput() {
        return DATE_FORMAT_OUTPUT;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String toFileString();

    public abstract void fromFileString(String fileString) throws DukeException;
}
