package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.messages.Messages;

/**
 * Represents a Deadline Task
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Constructor
     * @param description deadline task description
     * @param deadline deadline date
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    // Override toString method
    @Override public String toString() {
        return "[D] " + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern(Messages.DATE_FORMAT.getMessage())) + ")";
    }

}
