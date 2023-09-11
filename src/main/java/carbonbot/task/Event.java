package carbonbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents task that start at a specific date/time and ends at a specific date/time
 */
public class Event extends Task {
    private static final String TASK_ICON = "[E]";
    private static final DateTimeFormatter SERIALIZE_DT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_DT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the provided description, start datetime, and end datetime.
     *
     * @param description Description of the task
     * @param from        Start date / time
     * @param to          End date / time
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String serialize() {
        return String.format("E | %d | %s | %s | %s",
                super.isDone ? 1 : 0,
                super.description,
                this.from.format(SERIALIZE_DT_FORMATTER),
                this.to.format(SERIALIZE_DT_FORMATTER));
    }

    @Override
    public String toString() {
        return TASK_ICON + super.toString()
                + " (from: " + this.from.format(OUTPUT_DT_FORMATTER)
                + " to: " + this.to.format(OUTPUT_DT_FORMATTER) + ")";
    }
}
