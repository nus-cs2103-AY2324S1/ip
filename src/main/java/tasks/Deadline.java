package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String TaskIcon = "[D]";
    private LocalDateTime deadline;

    /**
     * A public constructor to initialize a Deadline task
     *
     * @param description  a description of the task
     * @param deadline a datetime instance of the deadline of task
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * A public constructor to initialize a Deadline task
     *
     * @param description  a description of the task
     * @param deadline a datetime instance of the deadline of task
     * @param isDone task completion status
     */
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
