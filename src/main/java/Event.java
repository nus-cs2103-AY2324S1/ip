/**
 * The Event class contains the tasks
 * with to and from date/time inputs.
 *
 * @author: Shishir
 **/
public class Event extends Task {

    /** The from date/time **/
    private String from;

    /** The to date/time **/
    private String to;

    /** The constructor.
     * @param description The description of the task.
     * @param from The from date/time
     * @param to The to date/time
     **/
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /** The constructor.
     * @param description The description of the task.
     * @param status The status of completion.
     * @param from The from date/time.
     * @param to The to date/time.
     **/
    public Event(String description, String from, String to, String status) {
        super(description, status);
        this.from = from;
        this.to = to;
    }

    /** The string representation of the task.
     * @return The string representation
     * **/
    @Override
    public String toString() {
        return "[Event] " + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    public String toFile() {
        return "E" + super.toFile() + " | " + this.from + "-" + this.to;
    }
}
