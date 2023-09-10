import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime dateTime;
    protected LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Constructor for tasks with date and time
    public Task(String description,LocalDateTime dateTime) {
        this(description);
        this.dateTime = dateTime;
    }

    // Getter and setter for dateTime
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X] " + description);
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [ ] " + description);
    }

    @Override
    public String toString() {
        String status = "[" + getStatusIcon() + "] ";
        return status + description;
    }

}
