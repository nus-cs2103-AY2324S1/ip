package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime deadline;

    public Deadline(String message, LocalDateTime deadline) {
        super(message);
        this.deadline = deadline;
    }

    public String toSaveFormatString() {
        return "D | " + this.getStatusNumber() + " | " + this.message + " | " + deadline;
    }

    public String toString() {
        return "[D]" + this.getStatusIcon() + " " + this.message
                + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss")) + ")";
    }
}
