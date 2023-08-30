import jdk.jfr.Event;

/**
 * Encapsulates an event with a start and end time/date.
 * Inherits from Task class.
 * @author Wu Jingya
 */
public class EventTask extends Task{
    private String from;
    private String to;

    /**
     * Creates an Event Task with the specified description and start and end time/date
     * @param description The task's description
     * @param from The task's starting time/date
     * @param to The task's ending time/date
     */
    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public EventTask(String description, String from, String to, Boolean done) {
        super(description, done);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the task
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toData() {
        return "E|" + super.toData() + "|" + from + "|" + to;
    }
}
