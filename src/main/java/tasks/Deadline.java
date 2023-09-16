package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task, with a description and the due date.
 */
public class Deadline extends Task {

    private String taskIcon = "[D]";
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
        String message = String.format("%s[%s] %s (by: %s)", this.taskIcon, this.getStatusIcon(),
                this.getDescription(), this.getDeadlineDate());
        return message;
    }

    /**
     * Parses the LocalDateTime of the deadline datetime to a String representation
     *
     * @return A String representation of LocalDateTime of deadline
     */
    public String getDeadlineDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedDateTime = this.deadline.format(formatter);
        return formattedDateTime;
    }
}
