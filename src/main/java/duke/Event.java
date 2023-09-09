package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Locale;

public class Event extends Task {
    // protected String from;
    // protected String to;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Initializes a new Event task.
     *
     * @param description       The description of the event task.
     * @param startDateTimeString  The start date and time of the event in the format of "yyyy-MM-dd HHmm".
     * @param endDateTimeString   The end date and time of the event in the format of "yyyy-MM-dd HHmm".
     */
    public Event(String description, String startDateTimeString, String endDateTimeString) {
        super(description);
        this.startTime = parseDateTime(startDateTimeString);
        this.endTime = parseDateTime(endDateTimeString);
    }

    private LocalDateTime parseDateTime (String dateTimeString) {
        try {
            if (!dateTimeString.isEmpty()) {
                return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            }
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date and time: " + dateTimeString);
            return null; // You may want to handle this case differently
        }
        return null;
    }

    private String formatDateTime (LocalDateTime dateTime) {
        if (dateTime == null) {
            return "Invalid Date and Time!";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a", Locale.ENGLISH);
        return dateTime.format(formatter);
    }

    private String saveFormatDateTime (LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return dateTime.format(formatter);
    }

    /**
     * Converts the event task to a formatted string.
     *
     * @return  A string representation of the event task.
     */
    @Override
    public String toDataString() {
        return "EVENT | " + super.toDataString() + " | " + saveFormatDateTime(startTime) + " | " + saveFormatDateTime(endTime);
    }

    /**
     * Converts the event task to a string representation for displaying.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDateTime(startTime) + " to: " + formatDateTime(endTime) + ")";
    }
}
