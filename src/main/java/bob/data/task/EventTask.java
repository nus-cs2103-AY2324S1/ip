package bob.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bob.data.exception.DukeException;

/**
 * Represents an EventTask that is stored in the TaskList.
 */
public class EventTask extends Task {
    /** The start date of the EventTask. */
    private LocalDateTime startDate;
    /** The end date of the EventTask. */
    private LocalDateTime endDate;
    /** The format for the DateTimeFormatter to follow. */
    private DateTimeFormatter formatter;

    /**
     * Constructs an EventTask with the specified description, start date, and end date.
     *
     * @param description The description of the task.
     * @param startDate The start datetime of the task.
     * @param endDate The end datetime of the task.
     * @throws DukeException If the end datetime is before or the same as the start datetime.
     */
    public EventTask(String description, String startDate, String endDate)
            throws DukeException, DateTimeParseException {
        super(description);
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime start = LocalDateTime.parse(startDate, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate, formatter);
        if (end.isBefore(start)) {
            throw new DukeException("Your start date is either the same or after your end date!");
        }
        this.startDate = start;
        this.endDate = end;
    }

    /**
     * Returns the type of task as a String.
     *
     * @return The type of task.
     */
    @Override
    public String getType() {
        return "Event";
    }

    /**
     * Returns the formatted(dd/MM/yyyy HHmm) deadline date as a String.
     *
     * @return Datetime of the deadline.
     */
    @Override
    public String getDateTime() {
        return formatter.format(this.startDate) + "," + formatter.format(this.endDate);
    }

    /**
     * Returns the string representation of the EventTask.
     *
     * @return A string representation of this EventTask.
     */
    @Override
    public String toString() {
        DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a");
        return "[E]" + super.toString() + " (from: " + stringFormatter.format(this.startDate)
                + " to: " + stringFormatter.format(this.endDate) + ")";
    }

    /**
     * Checks if this EventTask is the same as a specified object.
     * @param obj The object to be compared with.
     * @return true if they are both the same instance or have the same contents.
     *         false if they have different contents.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof EventTask) {
            EventTask object = (EventTask) obj;

            boolean sameContents = this.startDate.equals(object.startDate)
                    && this.endDate.equals(object.endDate);

            if (sameContents && super.equals(object)) {
                return true;
            }
        }
        return false;
    }
}
