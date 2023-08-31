package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that has a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Initializes a new Event object with the specified task name, start time, and end time.
     *
     * @param taskName The name of the event task.
     * @param start    The start time of the event.
     * @param end      The end time of the event.
     */
    public Event(String taskName, LocalDateTime start, LocalDateTime end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    /**
     * Retrieves the type of the task.
     *
     * @return The type of the task (in this case, "E" for Event).
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Retrieves the description of the task, along with the formatted start and end times.
     *
     * @return The formatted description of the event task including start and end times.
     */
    @Override
    public String getDescription() {
        String formattedStart = this.start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        String formattedEnd = this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return super.toString() + " | " + formattedStart + " | " + formattedEnd;
    }

    /**
     * Retrieves the formatted start time.
     *
     * @return The formatted start time in the format "d MMM yyyy h:mma".
     */
    public String getStartFormatted() {
        return this.start.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma"));
    }

    /**
     * Retrieves the formatted end time.
     *
     * @return The formatted end time in the format "d MMM yyyy h:mma".
     */
    public String getEndFormatted() {
        return this.end.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma"));
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getStart() {
        return this.start;
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDateTime getEnd() {
        return this.end;
    }

    /**
     * Generates the status and task name representation of the event task.
     *
     * @return A string representation of the event task's status, name, start time, and end time.
     */
    @Override
    public String statusAndTask() {
        return "[E]" + statusString() + " " + super.toString() +
                " (from: " + getStartFormatted() + ")(to: " + getEndFormatted() + ")";
    }
}

