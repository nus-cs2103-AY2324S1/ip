package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public LocalDateTime stringToDate(String dateTimeString) {
        DateTimeFormatter formatter;
        dateTimeString = dateTimeString.trim();
        if (dateTimeString.contains("T")) {
            formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        } else {
            formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        }
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    public String dateToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
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
            return String.format("E | %s | %s | %s - %s", isDone ? "1" : "0",
                    getDescription(), event.getFrom(), event.getTo());
        }
        return "";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
