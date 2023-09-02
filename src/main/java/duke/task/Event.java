package duke.task;

import duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that spans a specific time period.
 * An Event task contains a description, a start date and time, and an end date and time.
 */
public class Event extends Task {

    public LocalDateTime fromDateAndTime;
    public LocalDateTime toDateAndTime;

    /**
     * Constructs an Event task with the specified description, start date and time, and end date and time.
     *
     * @param description     The description of the Event task.
     * @param fromDateAndTime The start date and time of the event.
     * @param toDateAndTime   The end date and time of the event.
     */
    public Event(String description, LocalDateTime fromDateAndTime, LocalDateTime toDateAndTime) {
        super(description, Type.EVENT);
        this.fromDateAndTime = fromDateAndTime;
        this.toDateAndTime = toDateAndTime;
    }

    /**
     * Returns a string representation of the Event task.
     * The string includes the task type, status icon, description, and event time period.
     *
     * @return A formatted string representing the Event task.
     */
    @Override
    public String toString() {
        // example Oct 15 2019 2pm
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy ha");
        return "[E]" + "[" + getStatusIcon() + "] " + description + " (from: " + fromDateAndTime.format(formatter) +
                " to: " + toDateAndTime.format(formatter) + ")";
    }
}
