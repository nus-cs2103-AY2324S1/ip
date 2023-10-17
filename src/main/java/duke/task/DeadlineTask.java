package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    /** Represents the deadline of the task. */
    private LocalDate date;
    /**
     * Constructs a DeadlineTask object with the specified description and deadline.
     * @param description The description of the deadline task.
     * @param date The deadline of the task.
     */
    public DeadlineTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }
    /**
     * Returns a string representation of deadline task.
     * @return The method is returning a string representation of a deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
