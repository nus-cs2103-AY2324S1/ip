package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void changeStatus() {
        this.isDone = !this.isDone;
    }

    public LocalDateTime convertToDateTime(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(str, formatter);
    }

    public String saveTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return time.format(formatter);
    }

    public String displayTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return time.format(formatter);
    }

    public String getOutputString() {
        return String.format("X | %d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description; 
    }
}
