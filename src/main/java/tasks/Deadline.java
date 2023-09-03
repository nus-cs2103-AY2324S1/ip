package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String TaskIcon = "[D]";
    private LocalDateTime deadline;
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String getTaskAsString() {
        String message = String.format("%s[%s] %s (by: %s)", this.TaskIcon,this.getStatusIcon(), this.getDescription(), this.getDeadlineDate());
        return message;
    }

    public String getDeadlineDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedDateTime = this.deadline.format(formatter);
        return formattedDateTime;
    }
}
