/**
 * Event class representing a task and description
 */
public class Event extends Task{

    protected String from;
    protected String to;

    /**
     * Constructor for the Event class
     * @param description Description of the task
     * @param from start time of task
     * @param to end time of task
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from.split(" ", 2)[1];
        this.to = to.split(" ", 2)[1];
    }

    /**
     * String representation of the Event task
     * @return Information of event
     */
    @Override
    public String toString() {
        String ret = "[E] " + super.toString() + " (from: " + this.from + " to: " + this.to + ")" ;
        return ret;
    }
}
