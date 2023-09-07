package Tasks;

public class Event extends Task {
    /** The end of the event, as a string */
    private String eventEnd;
    /** The start of the event, as a string */
    private String eventStart;

    /**
     * Constructs an Event instance.
     *
     * @param name The name of the event.
     * @param isDone The completion status of the event.
     * @param eventStart The start of the event as a string.
     * @param eventEnd The end of the event as a string.
     */
    public Event(String name, boolean isDone, String eventStart, String eventEnd) {
        super(name, isDone);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", eventStart, eventEnd);
    }

    /**
     * {@inheritDoc}
     * Returns the string representation in the storage format for an Event.
     */

    public String toString(boolean isWritten) {
        String completionStr = super.isDone() ? "1" : "0";
        return "E" + " | " + completionStr + " | " + super.getName() + " | " + this.eventStart + " | " + this.eventEnd;
    }
}
