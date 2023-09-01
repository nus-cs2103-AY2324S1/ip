package deterministicparrot;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task in the Deterministic Parrot application.
 * An event task has a name and start and end date and time.
 */
public class Event extends Task {
    protected LocalDateTime timeStart;
    protected LocalDateTime timeEnd;

    /**
     * Constructs an Event object with the specified name, start time, and end time.
     *
     * @param name      The name of the event.
     * @param timeStart The start time of the event in string format.
     * @param timeEnd   The end time of the event in string format.
     * @throws DateTimeParseException If the input time strings are not in the expected format.
     * @throws IllegalArgumentException If the start time is after the end time.
     */
    public Event(String name, String timeStart, String timeEnd) throws DateTimeParseException {
        super(name);
        try {
            this.timeStart = DPUtils.dPTryParseDateTime(timeStart);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for 'timeStart'. Please provide a valid date.", e);
        }

        try {
            this.timeEnd = DPUtils.dPTryParseDateTime(timeEnd);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for 'timeEnd'. Please provide a valid date.", e);
        }

        if (this.timeStart.isAfter(this.timeEnd)) {
            throw new IllegalArgumentException("'timeStart' cannot be after 'timeEnd'.");
        }
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A formatted string containing the task type, name, and start and end times.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), DPUtils.dPFormatDateTime(this.timeStart), DPUtils.dPFormatDateTime(this.timeEnd));
    }
}
