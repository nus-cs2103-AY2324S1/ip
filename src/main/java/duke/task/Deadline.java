package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime deadlineDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    /**
     * Initialises the deadline task with the given description and deadline date.
     * 
     * @param description  The description of the deadline task.
     * @param deadlineDate The deadline date of the deadline task.
     */
    public Deadline(String description, LocalDateTime deadlineDate) {
        super(description, "D");
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadlineDate.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        return super.toFileString() + " | " + deadlineDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;
            return this.description.equals(deadline.description) && this.deadlineDate.equals(deadline.deadlineDate);
        }
        return false;
    }
}