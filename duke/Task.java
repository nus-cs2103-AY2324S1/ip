package duke;
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
    public Task(String description, LocalDateTime dateTime) {
        this(description);
        this.dateTime = dateTime;
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String status = "[" + getStatusIcon() + "] ";
        return status + description;
    }

}
