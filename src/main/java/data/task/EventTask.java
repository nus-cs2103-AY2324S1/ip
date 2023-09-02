package data.task;

import data.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
    public EventTask(String description, String startDate, String endDate) throws DukeException {
        super(description);
        try {
            this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            // time should be in format dd/mm/yyyy HHMM(24h)
            LocalDateTime start = LocalDateTime.parse(startDate, formatter);
            LocalDateTime end = LocalDateTime.parse(endDate, formatter);
            if (start.isBefore(end)) {
                this.startDate = start;
                this.endDate = end;
            } else {
                throw new DukeException("Your start date is either the same or after your end date!");
            }
        } catch (DateTimeParseException e) {
            System.out.println("There was an error parsing the date given.");
            e.printStackTrace();
        }
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
        return "[E]" + super.toString() + " (from: " + stringFormatter.format(this.startDate) +
                " to: " + stringFormatter.format(this.endDate) + ")";
    }
}
