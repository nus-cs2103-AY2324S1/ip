package joe.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a specific start and end time.
 */
public class EventTask extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private static final String DATETIME_FORMAT = "dd MMM yyyy HH:mm";

    /**
     * Constructs an EventTask with the given description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the EventTask object to a formatted string.
     *
     * @return A string representation of the EventTask, including its start and end times.
     */
    @Override
    public String toString() {
        String fromString = this.from.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
        String toString = this.to.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
        return String.format("[E]%s (from: %s to: %s)", super.toString(), fromString, toString);
    }
}
