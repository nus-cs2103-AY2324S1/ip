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
        this.by = processDateTime(this.deadline);
    }

    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Returns an appropriate LocalDateTime or LocalDate representation based on the user's provided datetime
     *
     * @param deadline String which user use to represent one's deadline [Either in YYY-MM-DD or YYYY-MM-DD HHMM format]
     * @return Temporal Object containing the formatted LocalDateTime or LocalDate object
     * @throws DukeException in the event deadline is not provided in the right format
     */
    public Temporal processDateTime(String deadline) throws DukeException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            if (deadline.contains(" ")) {
                return LocalDateTime.parse(deadline, dateTimeFormatter);
            } else {
                return LocalDate.parse(deadline, dateFormatter);
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please check that the dates/times you provided are correct!");
        }
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
        } else {
            throw new UnsupportedOperationException("Unsupported Temporal type");
        }
    }

    /**
     * Checks if the current deadline is between the given LocalDateTime and X days after it.
     *
     * @param dateTime The LocalDateTime to start the range.
     * @return true if the deadline is within the range, false otherwise.
     */
    public boolean withinDeadlineXDays(LocalDateTime dateTime, Integer daysAfter) {
        LocalDateTime deadlineTime;
        if (this.by instanceof LocalDate) {
            deadlineTime = ((LocalDate) this.by).atTime(23, 59, 59);
        } else if (this.by instanceof LocalDateTime) {
            deadlineTime = (LocalDateTime) this.by;
        } else {
            throw new UnsupportedOperationException("Unsupported Temporal type");
        }
        LocalDateTime sevenDaysAfter = dateTime.plusDays(daysAfter);
        boolean isAfterToday = deadlineTime.isEqual(dateTime) || deadlineTime.isAfter(dateTime);
        boolean isBeforeDeadline = deadlineTime.isBefore(sevenDaysAfter);
        return isAfterToday && isBeforeDeadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.generateNewDateString() + ")";
    }
}