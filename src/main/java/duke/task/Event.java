package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.util.DateParser;
/**
 * Represents a task with a starting and ending date.
 */
public class Event extends Task {
    private String startDateTimeStr;
    private String endDateTimeStr;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * Constructs an Event object with the task description,
     * starting date (and time) of event and ending date (and time) of the event.
     *
     * @param description Description of the task.
     * @param startDateTimeStr Start date (and time) of the event
     * @param endDateTimeStr End date (and time) of the event
     */
    public Event(String description, String startDateTimeStr, String endDateTimeStr) {
        super(description);
        this.startDate = DateParser.parseDate(startDateTimeStr);
        this.endDate = DateParser.parseDate(endDateTimeStr);
        this.startDateTime = DateParser.parseDateTime(startDateTimeStr);
        this.endDateTime = DateParser.parseDateTime(endDateTimeStr);
        this.startDateTimeStr = startDateTimeStr;
        this.endDateTimeStr = endDateTimeStr;

    }

    /**
     * Formats the string representation of event object to write to the file.
     *
     * @return String representation of the event object to be written to the file.
     */
    @Override
    public String toFileString() {
        String type = "E";
        return type + " | " + (isDone() ? "1" : "0") + " | " + this.description + " | "
                + this.startDateTimeStr + " to " + this.endDateTimeStr;
    }

    /**
     * The string representation of event object.
     *
     * @return String representation of the event object.
     */
    @Override
    public String toString() {

        String startStr = startDateTimeStr;
        String endStr = endDateTimeStr;

        if (startDateTime != null) {
            startStr = startDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        } else if (startDate != null) {
            startStr = startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }

        if (endDateTime != null) {
            endStr = endDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        } else if (endDate != null) {
            endStr = endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }

        return String.format("[E]%s (from: %s to %s)", super.toString(), startStr, endStr);
    }

}
