package duke;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class that stores information about the task and duration of the event.
 * Inherits from the parent class Task.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalTime to;
    private String identifier;


    public Event(String taskDescription, LocalDateTime from, LocalTime to, boolean isDone) {
        super(taskDescription, isDone);
        this.from = from;
        this.to = to;
        this.identifier = "[E]";
    }
    @Override
    public String toString() {
        return this.identifier + super.toString()
                + "(from: " + from.format(DateTimeFormatter.ofPattern("LLL dd yyyy Ka"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("Ka")) + ")";
    }
}
