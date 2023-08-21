/**
 * The class that represents an event task.
 *
 * @author Zhong Han
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for the event task.
     *
     * @param description The description of the event task.
     * @param from The start time/date of the event task.
     * @param to The end time/date of the event task.
     */
    public Event(String description, String from, String to) {
        super(description.strip());
        this.from = from.strip();
        this.to = to.strip();
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return a string comprising the symbol, status,
     *      description, start and end time/date of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
