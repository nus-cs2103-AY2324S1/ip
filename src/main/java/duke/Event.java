package duke;

import java.time.DateTimeException;

/**
 * Event task with start and end time.
 */
public class Event extends Task {
    /** Start time. */
    private DateTimeOptional startTime;
    /** End time. */
    private DateTimeOptional endTime;
    /**
     * Takes a raw string and attempts to create an Event object from it.
     * The raw string must be in the format "description /from start_time /to end_time".
     * If the raw string is empty, does not contain a start time or end time, it throws a DukeException.
     * If the start time or end time cannot be parsed into a DateTimeOptional object, it throws a DukeException.
     *
     * @param rawLine The raw string to be parsed into an Event object.
     * @return An Event object representing the parsed task.
     * @throws DukeException If the raw string is empty or does not contain a start or end time.
     */
    public static Event create(String rawLine) throws DukeException {
        if (rawLine.length() == 0) {
            throw new DukeException("Err: Empty Description");
        }
        String[] instructions = rawLine.split(" /from ", 2);
        if (instructions.length != 2) {
            throw new DukeException("Err: No start time given. Format is in event <desc> /from <time> /to <time>");
        }
        String[] timeLine = instructions[1].split(" /to ", 2);
        if (timeLine.length != 2) {
            throw new DukeException("Err: No end time given. Format is in event <desc> /from <time> /to <time>");
        }

        DateTimeOptional startDate, endDate;

        try {
            startDate = DateTimeOptional.parseDateTime(timeLine[0]);
        } catch (DateTimeException e) {
            throw new DukeException.DukeDateTimeException(timeLine[0]);
        }

        try {
            endDate = DateTimeOptional.parseDateTime(timeLine[1]);
        } catch (DateTimeException e) {
            throw new DukeException.DukeDateTimeException(timeLine[1]);
        }

        return new Event(instructions[0], startDate, endDate);
    }

    /**
     * Constructs an Event object given the description, the start and the end.
     *
     * @param description The description of the task.
     * @param startTime The start time of the task as a DateTimeOptional object.
     * @param endTime The end time of the task as a DateTimeOptional object.
     */
    public Event(String description, DateTimeOptional startTime, DateTimeOptional endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gives a string representation of the task in file format.
     *
     * @return A string representation of the task in file format.
     */
    @Override
    public String fileString() {
        return String.format(
                "event %d %s /from %s /to %s",
                super.isDone ? 1 : 0,
                super.description,
                this.startTime,
                this.endTime
        );
    }
    /**
     * Gives a string representation of the task in display format.
     *
     * @return A string representation of the task in display format.
     */

    @Override
    public String toString() {
        return String.format(
                "[E][%s] %s (from: %s to: %s)",
                super.getStatusIcon(),
                super.description,
                this.startTime.displayText(),
                this.endTime.displayText()
        );
    }
}
