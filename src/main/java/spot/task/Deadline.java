package spot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructs a new Deadline object.
     *
     * @param description Description of the Deadline object.
     * @param deadline Due date of the Deadline object.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a new Deadline object with specified isDone field.
     *
     * @param description Description of the Deadline object.
     * @param isDone Boolean representing the state of completion of the Deadline object.
     * @param deadline Due date of the Deadline object.
     */
    public Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns String representation of the Deadline object.
     *
     * @return String representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    /**
     * Returns representation of the Deadline object to be stored
     * in a text file within the hard disk.
     *
     * @return Representation of the Deadline object to be stored.
     */
    @Override
    public String toLine() {
        return " D | " + super.toLine() + " | "
                + this.deadline.format(DateTimeFormatter.ofPattern("dd-MM-uuuu"));
    }

    /**
     * Returns whether the Deadline object falls on the specified date.
     *
     * @param date Specified date.
     * @return Boolean representing whether the Deadline object falls on the specified date.
     */
    @Override
    public boolean fallsOn(LocalDate date) {
        return date.isEqual(this.deadline);
    }
}
