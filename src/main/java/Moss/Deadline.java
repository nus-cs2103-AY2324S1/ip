package moss;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate date;

    /**
     * Constructs a Moss.Deadline object with the given description and deadline.
     *
     * @param description The description of the task.
     * @param date          The deadline for the task.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns the deadline for the task.
     *
     * @return The deadline for the task.
     */
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString(String x) {
        return "D | " + super.toString() + " | " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "";
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + date + "";
    }
}
