public class Event extends Task {

    /**
     * The starting time of the event
     */
    private String from;

    /**
     * The ending time of the event
     */
    private String to;

    /**
     * constructor for Deadline
     * 
     * @param from - the starting time of the event
     * @param to   - the ending time of the event
     * @param task - the description of the task created
     */
    public Event(String from, String to, String task) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", this.from, this.to);
    }
}