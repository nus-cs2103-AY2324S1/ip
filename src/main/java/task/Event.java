package task;

import java.time.LocalDateTime;

/**
 * Class representing an event task.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs an Event task with the specified name, start time, and end time.
     *
     * @param name  The name of the event task.
     * @param start The start time of the event task in string format (to be parsed).
     * @param end   The end time of the event task in string format (to be parsed).
     */
    public Event(String name, String start, String end) {
        super(name);
        this.start = LocalDateTime.parse(start, INPUT_FORMATTER);
        this.end = LocalDateTime.parse(end, INPUT_FORMATTER);
    }

    /**
     * Constructs an Event task with the specified name, start time, end time, and marked status.
     *
     * @param name   The name of the event task.
     * @param start  The start time of the event task in string format (to be parsed).
     * @param end    The end time of the event task in string format (to be parsed).
     * @param marked The marked status of the event task.
     */
    public Event(String name, String start, String end, Boolean marked) {
        super(name, marked);
        this.start = LocalDateTime.parse(start, INPUT_FORMATTER);
        this.end = LocalDateTime.parse(end, INPUT_FORMATTER);
    }

    @Override
    public Event mark() {
        return new Event(this.name, this.start.format(INPUT_FORMATTER), this.end.format(INPUT_FORMATTER), true);
    }

    @Override
    public Event unmark() {
        return new Event(this.name, this.start.format(INPUT_FORMATTER), this.end.format(INPUT_FORMATTER), false);
    }

    @Override
    public boolean hasConflictWith(Task t) {
        // Only events can conflict with other events
        if (!(t instanceof Event)) {
            return false;
        }
        Event other = (Event) t;
        boolean isStartTimeBeforeEndTime = this.start.isBefore(other.end);
        boolean isEndTimeAfterStartTime = this.end.isAfter(other.start);
        return isStartTimeBeforeEndTime && isEndTimeAfterStartTime;
    }

    @Override
    public String saveTask() {
        return String.format("E | %s | %s | %s", super.saveTask(),
                this.start.format(INPUT_FORMATTER), this.end.format(INPUT_FORMATTER));
    }

    /**
     * Returns a string representation of the Event task, including its name, start time, and end time.
     *
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(),
                this.start.format(OUTPUT_FORMATTER),
                this.end.format(OUTPUT_FORMATTER));
    }
}
