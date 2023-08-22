package tasks;
/**
 * Event task, a type of task that start at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task{
    private String from;
    private String to;
    
    /**
     * Initializes a new event with the given description and duration. 
     *
     * @param desc The description of the event
     * @param from The start date/time of the event
     * @param to The end date/time of the event
     */
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return Task type, status icon, description, start date/time and end date/time of the task
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
