package duke.task;

import java.time.LocalDateTime;

import duke.time.Time;


/**
 * A Class that handle Event.
 *
 * @author marioalvaro
 */
public class Event extends Task {
    /**
     * the type Icon
     */
    private static final String TYPE = "E";
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
        return "[" + TYPE + "]" + super.toString()
                + " (from: " + Time.toString(this.from) + " to: " + Time.toString(this.to) + ")";
    }

    /**
     * Method to create string that in match with the storing format.
     *
     * @return string that in match with the storing format.
     */
    @Override
    public String toDataString() {
        return this.TYPE + " / " + super.toDataString() + " / "
                + Time.toDataString(this.from) + " / " + Time.toDataString(this.to);
    }

    /**
     * Equals method to help checking duplicates.
     *
     * @param obj obj compared with.
     * @return if the object is equal or not.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Event) {
            Event event = (Event) obj;

            boolean equalDescription = this.description == event.description;
            boolean equalFrom = this.from == event.from;
            boolean equalTo = this.to == event.to;
            if (equalDescription && equalFrom && equalTo) {
                return true;
            }

            if (this.description == null || event.description == null) {
                return false;
            }

            boolean strictEqualDescription = this.description.equals(event.description);
            boolean strictEqualFrom = this.from.equals(event.from);
            boolean strictEqualTo = this.to.equals(event.to);
            return strictEqualDescription && strictEqualFrom && strictEqualTo;
        }

        return false;
    }

    /**
     * Check if the text is equal.
     *
     * @param obj Object to compare with.
     * @return if the text is equal or not.
     */
    @Override
    public boolean equalsText(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Event) {
            Event event = (Event) obj;

            return super.equals(event);
        }

        return false;
    }
}
