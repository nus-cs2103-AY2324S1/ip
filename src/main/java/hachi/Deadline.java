package hachi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline object, extending the Task object.
 * Includes an additional field to track the deadline.
 * Overrides its representations in storage and in string format.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructor for the Deadline object.
     * @param taskName Name of the task.
     * @param deadline Deadline of the task.
     */
    public Deadline(String taskName, LocalDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * Checks if supplied date is before the deadline
     *
     * @return true if given date is before deadline, false otherwise
     */
    public boolean isDateWithinRange(LocalDate date) {
        return date.compareTo(deadline) <= 0;
    }

    /**
     * Converts hachi.Deadline object to its string representation when stored in the hard drive.
     *
     * @return String representation when stored in text file on user's hard drive
     */
    @Override
    public String toData() {
        return "D" + " | " + super.toData() + " | " + deadline.format(DateTimeFormatter.ISO_DATE);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}