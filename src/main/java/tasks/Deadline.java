package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dueDate;

    /*
     * Constructor for Deadline object.
     *
     * @param dueDate the LocalDateTime for the deadline.
     * @param description The description of this deadline.
     */
    public Deadline(LocalDateTime dueDate, String description) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String snooze() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy ha");
        this.dueDate = this.dueDate.plusDays(1);
        return "I have snoozed your deadline! It is now due by " + this.dueDate.format(format) + "\n";
    }

    /*
     * Returns the string version of the deadline.
     *
     * @return The String version of the deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy ha");
        return "[D]" + super.toString() + "(by: " + this.dueDate.format(format) + ")";
    }

    /*
     * Returns the string version of the deadline, for use in writing to storage.
     *
     * @return The string version of the deadline.
     */
    @Override
    public String toStringWithDateTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy ha");
        return "[D]" + super.toString() + " DATETIME " + this.dueDate.format(format);
    }
}
