package pogo.tasks;

import java.time.LocalDateTime;

import pogo.tasks.exceptions.PogoInvalidTaskException;

/**
 * Represents an event task, which has a start and end datetime.
 */
public class Event extends Task {
    /**
     * The start datetime of the event.
     */
    protected LocalDateTime from;

    /**
     * The end datetime of the event.
     */
    protected LocalDateTime to;

    /**
     * Creates an Event task.
     *
     * @param description Description of the task.
     * @param from        Start datetime of the event.
     * @param to          End datetime of the event.
     * @throws PogoInvalidTaskException If the start or end datetime is empty.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) throws PogoInvalidTaskException {
        super(description);
        if (from == null) {
            throw new PogoInvalidTaskException("Event start datetime cannot be empty");
        }
        this.from = from;

        if (to == null) {
            throw new PogoInvalidTaskException("Event end datetime cannot be empty");
        }
        this.to = to;
    }

    @Override
    public String getStatusMessage() {
        return "[E]" + super.getStatusMessage() + " (from: " + this.getFrom() + " to: " + this.getTo() + ")";
    }

    /**
     * Returns the start datetime of the event.
     *
     * @return Start datetime of the event.
     */
    public String getFrom() {
        return this.from.format(Task.DATETIME_FORMAT);
    }

    /**
     * Returns the end datetime of the event.
     *
     * @return End datetime of the event.
     */
    public String getTo() {
        return this.to.format(Task.DATETIME_FORMAT);
    }

    /**
     * Accepts a visitor that performs an action on the task.
     *
     * @param visitor Visitor to perform an action on the task.
     */
    public void accept(TaskVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Checks if the event is between a specified date.
     * The entire duration of the event must be between the specified date.
     */
    @Override
    public boolean isBetween(LocalDateTime start, LocalDateTime end) {
        return this.from.isAfter(start) && this.to.isBefore(end);
    }
}
