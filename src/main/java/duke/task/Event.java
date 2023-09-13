package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a given start and end date.
 */
public class Event extends Task {
    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;

    /**
     * Constructor for Event class.
     * @param description The description for the Event.
     * @param fromDate The start date for the Event.
     * @param toDate The end date for the Event.
     */
    public Event(String description, LocalDateTime fromDate, LocalDateTime toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;

        assert this.fromDate != null : "fromDate of the Event cannot be null";
        assert this.toDate != null : "toDate of the Event cannot be null";
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        String taskType = "[E]";
        String status = "[" + (this.isDone ? "X" : " ") + "]";
        String description = " " + super.description + " ";
        String duration = "(from: " + this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + " to: " + this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";

        return taskType + status + description + duration;
    }

    /**
     * Returns a string representation of the Event object in the data file format.
     *
     * @return String representation of the Deadline object in the data file format.
     */
    @Override
    public String toDataFormatString() {
        String taskType = "E | ";
        String status = (super.isDone ? "1" : "0") + " | ";
        String description = super.description + " | ";
        String dates = this.fromDate.toString() + " | " + this.toDate.toString();

        return taskType + status + description + dates;
    }
}
