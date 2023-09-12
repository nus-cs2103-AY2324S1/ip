package duke.task;

import java.time.LocalDate;

/**
 * Represents an event with a given start and end date.
 */
public class Event extends Task {
    protected LocalDate fromDate;
    protected LocalDate toDate;

    /**
     * Constructor for Event class.
     * @param description The description for the Event.
     * @param fromDate The start date for the Event.
     * @param toDate The end date for the Event.
     */
    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        String status = "[" + (this.isDone ? "X" : " ") + "]";
        String duration = "(from: " + this.fromDate + " to: " + this.toDate + ")";
        return "[E]" + status + " " + this.description + " " + duration;
    }

    /**
     * Returns a string representation of the Event object in the data file format.
     *
     * @return String representation of the Deadline object in the data file format.
     */
    @Override
    public String toDataFormatString() {
        String outputString = "E | ";
        outputString += (super.isDone ? "1" : "0") + " | ";
        outputString += super.description + " | ";
        outputString += this.fromDate + " | " + this.toDate;
        return outputString;
    }
}
