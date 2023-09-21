package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline Class
 */

public class Deadline extends Task {

    protected String by;
    protected LocalDateTime byDateTime;

    /**
     * Constructor to create a new Deadline Task
     * @param description description about the deadline
     * @param by When the deadline should be completed
     * @param isDone Whether the deadline has been completed
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        this.by = byDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
    }

    /**
     * @return toString version of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * update time of a deadline
     * @param description updated time
     * @return Task has been updated ,essage
     */
    @Override
    public String updateTime(String description) {
        this.byDateTime = Parser.retrieveDeadlineTime(description);
        this.by = byDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
        return "That task has been updated: \n";
    }
}
