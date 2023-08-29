package pogo.tasks;

import pogo.tasks.exceptions.PogoInvalidTaskException;

/**
 * Represents an event task, which has a start and end datetime.
 */
public class Event extends Task {
    /**
     * The start datetime of the event.
     */
    protected String from;

    /**
     * The end datetime of the event.
     */
    protected String to;

    public Event(String description, String from, String to) throws PogoInvalidTaskException {
        super(description);
        if (from.equals("")) {
            throw new PogoInvalidTaskException("Event start datetime cannot be empty");
        }
        this.from = from;

        if (to.equals("")) {
            throw new PogoInvalidTaskException("Event end datetime cannot be empty");
        }
        this.to = to;
    }

    @Override
    public String getStatusMessage() {
        return "[E]" + super.getStatusMessage() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Returns the start datetime of the event.
     *
     * @return Start datetime of the event.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns the end datetime of the event.
     *
     * @return End datetime of the event.
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Accepts a visitor that performs an action on the task.
     *
     * @param visitor Visitor to perform an action on the task.
     */
    public void accept(TaskVisitor visitor) {
        visitor.visit(this);
    }

    public static Event fromFormattedString(String input) throws PogoInvalidTaskException {
        String[] split = input.split(" \\| ");
        if (split.length != 5) {
            throw new PogoInvalidTaskException();
        }

        if (!split[0].equals("E")) {
            throw new PogoInvalidTaskException();
        }

        if (!split[1].equals("1") && !split[1].equals("0")) {
            throw new PogoInvalidTaskException();
        }

        boolean isDone = split[1].equals("1");

        Event event = new Event(split[2], split[3], split[4]);
        if (isDone) {
            event.markAsDone();
        }

        return event;
    }
}
