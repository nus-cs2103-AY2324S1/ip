package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

public class Deadline extends Task {
    protected Temporal by;
    protected String deadline;

    /**
     * Returns a Deadline object if provided with description (String) and the appropriate string format for deadline.
     * If the deadline provided is not of (YYYY-MM-DD or YYYY-MM-DD HHMM) format, Object will not be created.
     *
     * @param description description of task
     * @param deadline due date for tasks. Has to be represented in yyyy-mm-dd or yyyy-mm-dd hhmm format
     * @throws DukeException if format is provided wrongly, Deadline Object cannot be instantiated.
     */
    public Deadline(String description, String deadline) throws DukeException {
        super(description, "D");
        this.deadline = deadline;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            if (deadline.contains(" ")) {
                this.by = LocalDateTime.parse(deadline, dateTimeFormatter);
            } else {
                this.by = LocalDate.parse(deadline, dateFormatter);
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please check that the dates/times you provided are correct!");
        }
    }

    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Returns MMM-D-YYYY representation for the deadline assuming the deadline provided is of LocalDate/LocalDateTime Object.
     * If the deadline isn't of LocalDate/LocalDateTime, it throws an UnsupportedOperationException.
     *
     * @return MMM-D-YYYY representation from LocalDate or LocalDateTime object
     * @throws UnsupportedOperationException If object is not of LocalDate or LocalDateTime
     */
    public String generateNewDateString() {
        if (this.by instanceof LocalDate) {
            return ((LocalDate) this.by).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else if (this.by instanceof LocalDateTime) {
            return ((LocalDateTime) this.by).format(DateTimeFormatter.ofPattern("MMM d yyyy, ha"));
        }
        throw new UnsupportedOperationException("Unsupported Temporal type");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.generateNewDateString() + ")";
    }
}