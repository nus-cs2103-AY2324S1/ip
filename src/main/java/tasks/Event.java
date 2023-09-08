package tasks;

import dogebot.DateTimeHandler;

/**
 * The Event class stores the description of an event task and its start and end date/time.
 *
 * @author Kenvyn Kwek
 */
public class Event extends Task {
    private DateTimeHandler start;
    private DateTimeHandler end;

    /**
     * Initializes an event task.
     *
     * @param description Description of the event task.
     * @param start Start date and time.
     * @param end End date and time.
     * @param isDone If the task has already been completed.
     */
    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.start = new DateTimeHandler(start);
        this.end = new DateTimeHandler(end);
    }

    /**
     * String representation of an event task.
     *
     * @return Event task details.
     */
    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + start.toString() + " - " + end.toString();
    }
}
