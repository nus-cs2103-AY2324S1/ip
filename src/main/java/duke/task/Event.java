package duke.task;

import duke.time.Time;

import java.time.LocalDateTime;

/**
 * A Class that handle Event.
 *
 * @author marioalvaro
 */
public class Event extends Task {
    /**
     * the type Icon
     */
    private final String TYPE = "E";
    /**
     * start of the event
     */
    private LocalDateTime from;
    /**
     * end of the event
     */
    private LocalDateTime to;

    /**
     * A Constructor to create an Event
     * @param description the text description of the task
     * @param from The time the event starts
     * @param to The time the event ends
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = Time.toLocalDateTime(from);
        this.to = Time.toLocalDateTime(to);
    }

    /**
     * override the toString method
     *
     * @return a string
     */
    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() +
                " (from: " + Time.toString(this.from) + " to: " + Time.toString(this.to) + ")";
    }

    /**
     * Method to create string that in match with the storing format.
     *
     * @return string that in match with the storing format.
     */
    @Override
    public String toDataString() {
        return this.TYPE + " / " + super.toDataString() + " / " +
                Time.toDataString(this.from) + " / " + Time.toDataString(this.to);
    }
}
