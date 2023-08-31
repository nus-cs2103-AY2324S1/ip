/**
 * Event is the main class for Event task used by the Sidtacphi bot.
 */
public class Event extends Task {
    private String start = "";
    private String end = "";

    /**
     * Constructor for the Event class.
     * 
     * @param name
     */
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Shows whether the task is completed and the name of the task.
     * 
     * @return Type of task, completion state of task and the name of task.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + start + ", to: " + end + ")";     
    }
}
