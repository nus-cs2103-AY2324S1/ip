package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
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
}