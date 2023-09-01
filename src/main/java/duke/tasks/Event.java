package duke.tasks;

import duke.Duke;

import java.time.LocalDateTime;

public class Event extends Task {

    /** Start time for the event */
    protected LocalDateTime from;
    /** End time for the event */
    protected LocalDateTime to;

    /**
     * Constructs an Event object.
     *
     * @param description Description of the Event.
     * @param from Start time of the Event.
     * @param to End time of the Event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event object.
     *
     * @param description Description of the Event.
     * @param isDone Status of the Event.
     * @param from Start time of the Event.
     * @param to End time of the Event.
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns text representation of deadline for data file.
     *
     * @return Text representation of Event.
     */
    public String getTextRepresentation() {
        return "E | " + super.getTextRepresentation() + " | " + this.from.format(Duke.TIME_FORMAT) + " | "
                + this.to.format(Duke.TIME_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(Duke.TIME_FORMAT)
                + " to: " + this.to.format(Duke.TIME_FORMAT) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Event)) {
            return false;
        } else {
            Event event = (Event) o;
            return event.from.equals(this.from)
                    && event.to.equals(this.to)
                    && event.description.equals(this.description)
                    && event.isDone == this.isDone;
        }
    }
}
