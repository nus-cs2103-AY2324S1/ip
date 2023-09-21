package jeo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task of type deadline.
 *
 * @author Joseph Oliver Lim
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a deadline with a specified description and date.
     *
     * @param description A string describing the deadline task.
     * @param by A string describing the deadline date.
     * @throws DateTimeParseException If the deadline date is not a valid date.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the String representation of the Deadline.
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Compares the Deadline Task to another task.
     *
     * @param o The object to be compared with.
     * @return The comparison value.
     */
    @Override
    public int compareTo(Task o) {
        if (o instanceof ToDo) {
            return 1;
        }
        if (o instanceof Deadline) {
            Deadline deadline = (Deadline) o;
            return this.by.compareTo(deadline.by);
        }
        if (o instanceof Event) {
            return -1;
        }
        return 0;
    }
}
