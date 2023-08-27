import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime dateTime;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.dateTime = null;
    }

    public Task(String description, LocalDateTime dateTime) {
        this.description = description;
        this.isDone = false;
        this.dateTime = dateTime;
    }

    public abstract String toFileString();

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        String formattedDescription = getStatusIcon() + " " + this.description;
        if (dateTime != null) {
            String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            return formattedDescription + " (at: " + formattedDateTime + ")";
        } else {
            return formattedDescription;
        }
    }
}
