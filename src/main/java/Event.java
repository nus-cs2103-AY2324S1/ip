public class Event extends Task {

    /**
     * The starting time of the event
     */
    private final String from;

    /**
     * The ending time of the event
     */
    private final String to;

    /**
     * constructor for Deadline
     *
     * @param from  - the starting time of the event
     * @param to    - the ending time of the event
     * @param task  - the description of the task created
     * @param input - Input that generated the task
     */
    public Event(String from, String to, String task, String input) {
        super(task, input);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", this.from, this.to);
    }
}