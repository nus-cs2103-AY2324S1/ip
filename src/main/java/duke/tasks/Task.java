package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private final String type;
    private final String description;
    private boolean isMarked;

    public Task(String description, String type, boolean isMarked) {
        this.description = description;
        this.type = type;
        this.isMarked = isMarked;
    }

    abstract public String getOriginalMessage();

    public String getDescription() {
        return this.description;
    }

    public boolean getMarked() {
        return this.isMarked;
    }

    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    public String stringifyDate(LocalDateTime dateTime) {
        String formatted = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm"));
        return String.join(" ", formatted.split("T"));
    }

    public String formatDate(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("HHmm, MMM d yyyy"));
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.type.substring(0,1).toUpperCase(), this.getStatusIcon(), this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof Task) {
            Task t = (Task) o;
            return this.isMarked == t.isMarked && this.description.equals(t.description);
        }

        return false;
    }

    private String getStatusIcon() {
        return (isMarked ? "X" : " "); //return tick or X symbols
    }
}
