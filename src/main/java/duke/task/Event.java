package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import duke.DukeException;

/**
 * Represents an event task with a start and end date.
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);

    private final LocalDate eventStart;
    private final LocalDate eventEnd;

    /**
     * Constructs a new Event with the specified description, start date, and end date.
     *
     * @param eventStart     Start date of the event.
     * @param eventEnd       End date of the event.
     * @param description Description of the event.
     * @throws DukeException If the given date format is invalid.
     */
    public Event(String eventStart, String eventEnd, String description) throws DukeException {
        super(description, TaskType.EVENT);
        try {
            this.eventStart = LocalDate.parse(eventStart, FORMATTER);
            this.eventEnd = LocalDate.parse(eventEnd, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format.");
        }
        assert !this.eventStart.isAfter(this.eventEnd) : "Event start date should not be after end date!";
    }

    /**
     * Constructs a new Event with the specified description, combined time (start-end), and completion status.
     *
     * @param description Description of the event.
     * @param time Combined start and end date in the format 'start-end'.
     * @param isDone Boolean indicating the completion status of the event.
     * @throws DukeException If the given date format is invalid.
     */
    public Event(String description, String time, Boolean isDone) throws DukeException {
        super(description, TaskType.EVENT, isDone);
        try {
            List<LocalDate> dates = Arrays.stream(time.split("-"))
                    .map(String::trim)
                    .map(dateStr -> LocalDate.parse(dateStr, FORMATTER))
                    .collect(Collectors.toList());

            if (dates.size() != 2) {
                throw new DukeException("Invalid date format. Expected start-end format.");
            }

            this.eventStart = dates.get(0);
            this.eventEnd = dates.get(1);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format.");
        }
    }

    public LocalDate getEventStart() {
        return eventStart;
    }

    public LocalDate getEventEnd() {
        return eventEnd;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return A string representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStart.format(OUTPUT_FORMATTER)
                + " to: " + eventEnd.format(OUTPUT_FORMATTER) + ")";
    }
}
