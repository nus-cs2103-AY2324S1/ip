package duke.task;

/**
 * Represents an event.
 */
public class Event extends Task {
    /** Represents when the event starts and ends. */
    protected String from;
    protected String to;

    /**
     * Constructor method.
     *
     * @param description Event description.
     * @param from Event start datetime.
     * @param to Event end datetime.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String stringToFile() {
        return String.format("E | %s | %s | %s", super.stringToFile(), from, to);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Event) {
            Event event = (Event) other;
            boolean isDescriptionEqual = this.description.equalsIgnoreCase(event.description);
            boolean isFromEqual = this.from.equals(event.from);
            boolean isToEqual = this.to.equals(event.to);
            return isDescriptionEqual && isFromEqual && isToEqual;
        } else {
            return false;
        }
    }
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }

}
