package simon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import simon.SimonException;


/**
 * The {@code Event} class extends the {@code Task} class and represents a task
 * that has a specific start and end date and time defining the duration of the event.
 */
public class Event extends Task {

    /** Formatter to parse date and time input provided by the user. */
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy[ HHmm]");

    /** Formatter to format date and time for output display. */
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /** The start date and time of this event. */
    private final LocalDateTime startDateTime;

    /** The end date and time of this event. */
    private final LocalDateTime endDateTime;

    /**
     * Constructs a new Event task with the given name, start date, and end date.
     *
     * @param taskName The name or description of the task.
     * @param startDate The start date and time string for this event.
     * @param endDate The end date and time string for this event.
     * @throws SimonException If the provided date and time format is incorrect or if end time is before start time.
     */
    public Event(String taskName, String startDate, String endDate) throws SimonException {
        super(taskName);
        try {
            // If no time specified, append " 0000"
            if (!startDate.contains(" ")) {
                startDate += " 0000";
            }
            if (!endDate.contains(" ")) {
                endDate += " 0000";
            }

            this.startDateTime = LocalDateTime.parse(startDate, INPUT_FORMATTER);
            this.endDateTime = LocalDateTime.parse(endDate, INPUT_FORMATTER);

            // Validate that endDateTime is after startDateTime
            if (!endDateTime.isAfter(startDateTime)) {
                throw new SimonException("The end time should be after the start time.");
            }

        } catch (DateTimeParseException e) {
            throw new SimonException("The date and time format is incorrect. Expected format: d/M/yyyy [HHmm].");
        }
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Converts this Event task to a string format suitable for display.
     *
     * @return A string representation of this Event task.
     */
    @Override
    public String toString() {
        return " [E]" + (super.getIsDone() ? "[X] " : "[ ] ") + super.toString()
                + " (from: " + startDateTime.format(OUTPUT_FORMATTER)
                + " to: " + endDateTime.format(OUTPUT_FORMATTER) + ")";
    }
}
