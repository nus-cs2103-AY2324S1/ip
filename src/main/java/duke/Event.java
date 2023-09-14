package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event
 */
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
     * Updating the time of an event
     * @param description updated time
     * @return confirmation message that the task's time has been updated
     */
    @Override
    public String updateTime(String description) throws InvalidInputException {
        LocalDateTime[] result = Parser.retrieveEventTime(description);
        LocalDateTime fromTime = result[0];
        LocalDateTime toTime = result[1];

        this.fromDateTime = fromTime;
        this.toDateTime = toTime;
        this.from = fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
        this.to = toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));

        return "This task has been updated: \n";
    }

    /**
     * @return toString of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
