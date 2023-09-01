package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The object that represents a deadline task.
 */
public class Deadline extends Task {

    private static final String TYPE = "[D]";
    protected LocalDate deadline;

    /**
     * Creates the deadline object.
     *
     * @param task Task description.
     * @param deadline deadline of the object.
     * @throws DateTimeParseException
     */
    public Deadline(String task, String deadline) throws DateTimeParseException {
        super(task);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Checks if the task deadline is on a given date.
     *
     * @param date the date to check on.
     * @return true if date matches task deadline.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return date.isEqual(this.deadline);
    }

    @Override
    public String toSaveFormat() {
        return "Deadline | " + super.toSaveFormat() + " | " + this.deadline;
    }

    @Override
    public String toString() {
        return Deadline.TYPE + super.toString() + " (by: " + this.deadline.format(
                DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }

        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline o = (Deadline) other;
        return this.deadline.equals(o.deadline);
    }
}
