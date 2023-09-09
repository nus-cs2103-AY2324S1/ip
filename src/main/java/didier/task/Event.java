package didier.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An Event is a Task that will take place over a specific time period. Therefore, it must keep track
 * of when the event starts and when the event ends.
 */
public class Event extends Task {

    private LocalDate from;
    private LocalDate to;

    /**
     * The constructor for the Event object. Event is marked as undone by default at the start.
     *
     * @param description The description of the event task.
     * @param from The start time of the event task.
     * @param to The end time of the event task.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * The constructor for the Event object that allows the user to specify whether the Event is done
     * or undone initially.
     *
     * @param description The description of the event task.
     * @param from The start time of the event task.
     * @param to The end time of the event task.
     * @param isDone Whether the task is done or not.
     */
    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String composeToFileString() {
        return String.format("E|%s|%s|%s", super.composeToFileString(), this.from.toString(), this.to.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            return this.from.equals(event.from) && this.to.equals(event.to) && super.equals(obj);
        }
        return false;
    }
}
