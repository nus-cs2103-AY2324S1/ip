/**
 * This class is a subclass of Task which defines an event task.
 */
public class Event extends Task {
    /** A String containing start of event. */
    private String from;
    /** A String containing end of event. */
    private String to;

    /**
     * A constructor of event
     * @param description Name of event
     * @param from Start of event
     * @param to End of event
     * @throws HelpBuddyException If description, start and end time of event is empty.
     */
    public Event(String description, String from, String to) throws HelpBuddyException {
        super(description);
        this.from = from;
        this.to = to;
        if (description.isBlank()) {
            throw new HelpBuddyException("The description of a event cannot be empty.\n");
        } else if (from.isBlank()) {
            throw new HelpBuddyException("Please enter a start time of event.\n");
        } else if (to.isBlank()) {
            throw new HelpBuddyException("Please enter an end time of event.\n");
        }
    }

    /**
     *
     * @return String representation of event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.from + "to:" + this.to + ")";
    }

    @Override
    public String stringifyTask() {
        return String.format("E|%d|%s|%s", this.isDone ? 1 : 0, this.description, this.from + " to" + this.to);
    }

}
