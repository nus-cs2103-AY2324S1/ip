package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a Task with a deadline.
 */
public class Deadline extends Task {
    /** Date and time the task is due **/
    private LocalDateTime dateTime;

    /**
     * Instantiates an instance of Deadline.
     *
     * @param description Description of the Deadline task.
     * @param dateTime Deadline for deadline task.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }

    @Override
    public String writeToFile() {
        int mark;
        if (super.getStatusIcon().equals("X")) {
            mark = 1;
        } else {
            mark = 0;
        }
        return "D | " + mark + " | " + super.writeToFile() + " | " + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }
}
