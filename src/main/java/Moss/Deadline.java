package moss;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {

    /**
     * The deadline date for the task.
     */
    private LocalDate date;

    /**
     * Constructs a new Deadline object with the given description and deadline date.
     *
     * @param description The description of the task.
     * @param date The deadline date for the task.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    
    /** Returns the deadline for the task.
     *
     * @return The deadline for the task.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @param x A placeholder parameter to differentiate this method signature.
     * @return A formatted string containing task type, description, and deadline date.
     */
    @Override
    public String toString(String x) {
        return "D | " + super.toString() + " | " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string containing task type, description, and unformatted deadline date.
     */
    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + date;
    }
}

