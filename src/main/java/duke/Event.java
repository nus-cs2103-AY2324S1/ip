package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Event extends Task {

    protected String from;
    protected String to;
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;


    /**
     * Constructor to create an Event Task.
     * @param description Description about the event task.
     * @param from When the event is from.
     * @param to When the event is done.
     * @param isDone Boolean if the event has been completed or not
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.fromDateTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        this.toDateTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        this.from = fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
        this.to = toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
    }

    /**
     * @return toString of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}