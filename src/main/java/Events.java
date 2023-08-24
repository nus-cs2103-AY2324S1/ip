/**
 * class for events
 */
public class Events extends Task{
    /**
     * For the start
     */
    private String start;
    /**
     * For the end
     */
    private String end;

    /**
     * The constructor
     * @param name the name of the event task
     * @param start The starting time
     * @param end The ending time
     */
    public Events (String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * To convert the task to string
     * @return a string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start + "to: " + end + ")";
    }
}