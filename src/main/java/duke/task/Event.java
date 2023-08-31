package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

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
    public Event(String description, String startDateTimeStr,String endDateTimeStr) {
        super(description);
        this.startDate = parseDate(startDateTimeStr);
        this.endDate = parseDate(endDateTimeStr);
        this.startDateTime = parseDateTime(startDateTimeStr);
        this.endDateTime = parseDateTime(endDateTimeStr);
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
        return type + " | " + (getIsDone() ? "1" : "0") + " | " + this.description + " | "
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

    /**
     * Parses a String to a LocalDate object
     *
     * @param date String representation of potential date
     * @return A LocalDate object if string can be parsed, null otherwise
     */
    private LocalDate parseDate(String date) {

        List<DateTimeFormatter> formatters = new ArrayList<>();
        //List of accepted data formats
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        formatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        formatters.add(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy"));

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException ignore){

            }
        }

        return null;

    }

    /**
     * Parses a String to a LocalDateTime object
     *
     * @param date String representation of potential date and time
     * @return A LocalDateTime object if string can be parsed, null otherwise
     */
    private LocalDateTime parseDateTime(String date) {

        List<DateTimeFormatter> formatters = new ArrayList<>();
        //List of accepted data formats
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")); //format from reading file
        formatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        formatters.add(DateTimeFormatter.ofPattern("MM/dd/yyyy HHmm"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(date, formatter);
            } catch (DateTimeParseException ignore) {

            }
        }
        return null;

    }

}
