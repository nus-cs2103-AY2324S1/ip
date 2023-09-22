package blip.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import blip.priority.Priority;
/**
 * Represents an Event task.
 */
public class Event extends Task {
    /**
     * The event's start date time.
     */
    protected LocalDateTime eventFrom;

    /**
     * The event's end date time.
     */
    protected LocalDateTime eventTo;

    /**
     * Creates an instance of Event, sets priority to a default MEDIUM.
     * @param description The description of the event task
     * @param eventFrom The date time of the start of event
     * @param eventTo The date time of the end of event
     * @param isDone Boolean that represents whether task is done
     */
    public Event(String description, LocalDateTime eventFrom, LocalDateTime eventTo, boolean isDone, Priority priority) {
        super(description, isDone, priority);
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
    }

    /**
     * Saves the event task to file in this specified format.
     * @return String representation of event task to save in data file
     */
    @Override
    public String saveToFileString() {
        return "E " + (super.isDone ? "| 1 | " : "| 0 | ")
                + (super.priority + " | ")
                + super.toString() + " | "
                + this.eventFrom.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                + " | "
                + this.eventTo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns a String representation of the event task.
     * @return String representation of event task
     */
    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon()  + super.getPriority() + super.toString() + " (from: "
                + this.eventFrom.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"))
                + " to: "
                + this.eventTo.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"))
                + ")";
    }
}
