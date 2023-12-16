package duke.tasks;

import java.time.LocalDateTime;

/**
 * A task object that tracks Events.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;


    /**
     * Public constructor for Event.
     *
     * @param description of the event object
     * @param from the LocalDateTime object tracking when the event starts
     * @param to the LocalDateTime object tracking when the event ends
     * @param isMarked boolean value if the Event task is marked
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isMarked) {
        super(description, "event", isMarked);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getOriginalMessage() {
        return String.format(
                "%s %s /from %s /to %s",
                this.type,
                this.getDescription(),
                this.stringifyDate(this.from),
                this.stringifyDate(this.to)
        );
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.formatDate(this.from) + " to: " + this.formatDate(this.to) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }

        if (o instanceof Event) {
            Event d = (Event) o;
            if (!this.from.equals(d.from)) {
                return false;
            }

            return this.to.equals(d.to);
        }

        return false;
    }
}
