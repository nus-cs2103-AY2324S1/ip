package task;

public class Event extends Task {
    /**
     * The date of the event.
     */
    private final String startDateTime;
    private final String endDateTime;

    /**
     * Creates an event with the given description and date.
     * @param description The description of the event.
     * @param startDateTime The startDateTime of the event.
     * @param endDateTime The endDateTime of the event.
     */
    public Event (String description, String startDateTime, String endDateTime) {
        super.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Returns the string representation of the event.
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDateTime + " to: " + endDateTime + ")";
    }

}
