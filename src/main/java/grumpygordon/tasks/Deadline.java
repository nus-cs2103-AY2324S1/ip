package grumpygordon.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    /**
     * End time of deadline.
     */
    protected LocalDateTime by;

    /**
     * Constructor to create a Deadline task.
     * @param description Description of event
     * @param by End time of event
     * @param isDone Boolean that represents whether the task is done
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a String representation of a Deadline task.
     * @return String representation of a Deadline task
     */
    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + super.toString()
                + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Returns the format in which an Event task will be saved.
     * @return String representation of the save format of an Event task
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + super.toString() + " | " + this.by;
    }
}
