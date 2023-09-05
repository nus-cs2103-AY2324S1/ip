package teho.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {
    private LocalDate byDate;

    /**
     * Constructs new Deadline task with description and deadline.
     *
     * @param description Description of task with a specific deadline.
     * @param byDate Due date of the task with a specific deadline.
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Returns a string representation of the Deadline task details.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + this.description
                + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a string representation of the Deadline task details for saving task in file.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String fileString() {
        String digitStatus = this.isDone? "1": "0";
        return "D|" + digitStatus + "|" + this.description + "|" + byDate;
    }
}
