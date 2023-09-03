package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulation of an Event task.
 */
public class Event extends Task {
    private final LocalDateTime from;

    private final LocalDateTime to;

    /**
     * Constructor for Event.
     * @param eventName Name of event.
     * @param from Date and time of event start.
     * @param to Date and time of event end.
     */
    public Event(String eventName, LocalDateTime from, LocalDateTime to) {
        super(eventName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format(
                "[E]%s (from: %s, to: %s)",
                super.toString(),
                this.from.format(formatter),
                this.to.format(formatter)
        );
    }

    @Override
    public String serialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return String.format(
                "event %s /from %s /to %s%s",
                getTaskName(),
                this.from.format(formatter),
                this.to.format(formatter),
                this.isDone() ? " /done" : ""
        );
    }
}
