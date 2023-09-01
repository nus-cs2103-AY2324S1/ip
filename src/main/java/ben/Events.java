package ben;

import java.time.LocalDateTime;

/**
 * Represents an events task.
 */
public class Events extends Task{
    /**
     * The date of when the event starts.
     */
    private final LocalDateTime from;

    /**
     * The date of when the event ends.
     */
    private final LocalDateTime to;

    /**
     * Constructor that takes in description of the event, whether it is completed, the from and to date of the event.
     *
     * @param description The description of the event.
     * @param isCompleted Whether the event is completed.
     * @param from The date of when the event starts.
     * @param to The date of when the event ends.
     */
    public Events(String description, boolean isCompleted, LocalDateTime from, LocalDateTime to) {
        super(description, isCompleted);
        this.from = from;
        this.to = to;
    }

    /**
     * String representation of the event.
     *
     * @return String representation of events.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateFormat(from) + "HRS to: " + dateFormat(to) + "HRS)";
    }

    /**
     * String representation of events when it is saved to the file.
     *
     * @return String representation of events.
     */
    @Override
    public String saveString() {
        return "E|" + super.saveString() + "|" + saveDateFormat(from) + "|" + saveDateFormat(to);
    }
}
