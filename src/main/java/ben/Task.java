package ben;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String description;
    private Boolean isCompleted;

    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public void mark() {
        isCompleted = true;
    }

    public void unmark() {
        isCompleted = false;
    }

    @Override
    public String toString() {
        String marking;
        if (isCompleted) {
            marking = "[X]";
        } else {
            marking = "[ ]";
        }
        return marking + " " + description;
    }

    public String saveString() {
        return isCompleted.toString() + "|" + description;
    }

    public String dateFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    public String saveDateFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }
}
