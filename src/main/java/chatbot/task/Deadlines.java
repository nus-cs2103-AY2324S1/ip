package chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private LocalDateTime date;

    public Deadlines(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + date.format(formatter) + ")";
    }

    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dateStr = date.format(formatter);
        String done = isDone ? "1" : "0";
        return String.format("D | %s | %s | %s", done, description, dateStr);
    }


    public LocalDateTime getDateTime() {
        return date;
    }
}
