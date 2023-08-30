package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task which is a type of Task with a starting and ending date-time.
 */
public class Event extends Task {

    /**
     * Represents the type identifier for the Event.
     */
    private static final String TYPE = "[E]";

    /**
     * Starting date and time of the event.
     */
    private LocalDateTime from;

    /**
     * Ending date and time of the event.
     */
    private LocalDateTime to;

    /**
     * Constructs a new Event object with a given task name, starting, and ending date-time.
     *
     * @param name Name or description of the event task.
     * @param from Starting date and time of the event.
     * @param to Ending date and time of the event.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the Event task to a formatted string suitable for saving in a file.
     *
     * @return Formatted string representing the task.
     */
    @Override
    public String stringifyTask() {
        return String.format("E|%d|%s|%s|%s", this.done ? 1 : 0, this.name
                , from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                , to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return String representation of the task, including its type, status, name, start, and end time.
     */
    @Override
    public String toString() {
        return TYPE + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
