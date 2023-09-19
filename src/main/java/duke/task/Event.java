package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidFormatException;

/**
 * Event instance of a task.
 */
public class Event extends Task {
    private final LocalDate fromDate;
    private String fromTime = "";
    private final LocalDate toDate;
    private String toTime = "";

    /**
     * Constructs a event with a given description. Completion of the task
     * is false by default.
     * @param description The description of the task
     * @param from The start time of the event
     * @param to The end time of the event
     */
    public Event(String description, String from, String to) throws InvalidFormatException {
        super(description);
        String[] fromTemp = from.split("\\s+");
        String[] toTemp = to.split("\\s+");
        try {
            this.fromDate = LocalDate.parse(fromTemp[0]);
            this.toDate = LocalDate.parse(toTemp[0]);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException(e.getMessage());
        }
        if (fromTemp.length == 2) {
            this.fromTime = fromTemp[1];
        }
        if (toTemp.length == 2) {
            this.toTime = toTemp[1];
        }
    }

    /**
     * Returns a formatted string of the status of the task.
     * @return String containing completion status, type, description, from and to of task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + formattedFromDate() + " "
                + this.fromTime + " to: " + formattedToDate()
                + " " + this.toTime + ")";
    }

    /**
     * Returns a string formatted in the way it is to be saved.
     * @return Formatted string to be written into file
     */
    @Override
    public String toSaveFormat() {
        return "E | " + super.completionStatus() + " | " + this.description + " | "
                + this.fromDate + " " + this.fromTime + "->"
                + this.toDate + " " + this.toTime;
    }

    /**
     * formats from date for display
     * @return string of formatted from date
     */
    private String formattedFromDate() {
        return this.fromDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * formats to date for display
     * @return string of formatted to date
     */
    private String formattedToDate() {
        return this.toDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

}
