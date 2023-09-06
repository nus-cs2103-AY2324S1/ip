package ipbot.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Represents an event, with a description, start time and end time.
 */
public class Event extends Task {

    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Attempts to parse a start time String and end time String to LocalDateTime.
     * Then, defines an Event object with the given description, start time and end time.
     *
     * @param description The description of the event.
     * @param startTimeStr The start time of the event as a String.
     * @param endTimeStr The end time of the event as a String.
     * @throws DateTimeParseException
     */
    public Event(String description, String startTimeStr, String endTimeStr) throws DateTimeParseException {
        this(
                description,
                LocalDateTime.parse(startTimeStr, Task.INPUT_DATE_TIME_FORMATTER),
                LocalDateTime.parse(endTimeStr, Task.INPUT_DATE_TIME_FORMATTER));
    }

    /**
     * Defines an Event object with the given description, start time and end time.
     * Throws an exception if the start time is after the end time.
     *
     * @param description The description of the event.
     * @param startTime The start time of the event.
     * @param endTime  The end time of the event.
     * @throws IllegalArgumentException
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) throws IllegalArgumentException {
        super(description);
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time of event cannot be after end time!");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Checks if an event is ongoing at the given time.
     *
     * @param dateTime The time to check if the event is ongoing.
     * @return True if the given time is between the start time and the end time of the event. False otherwise.
     */
    public boolean isOngoing(LocalDateTime dateTime) {
        return !this.startTime.truncatedTo(ChronoUnit.DAYS).isAfter(dateTime.truncatedTo(ChronoUnit.DAYS))
                && !dateTime.truncatedTo(ChronoUnit.DAYS).isAfter(this.endTime.truncatedTo(ChronoUnit.DAYS));
    }

    @Override
    public String toCommaString() {
        String[] commaStringValues = {
                "E",
                this.isDone ? "X" : " ",
                this.description,
                this.startTime.format(Task.INPUT_DATE_TIME_FORMATTER),
                this.endTime.format(Task.INPUT_DATE_TIME_FORMATTER),
        };
        String commaString = String.join(",", commaStringValues);
        return commaString;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString()
                + " (from: " + this.startTime.format(Task.DISPLAY_DATE_TIME_FORMATTER)
                + " to: " + this.endTime.format(Task.DISPLAY_DATE_TIME_FORMATTER) + ")";
    }
}
