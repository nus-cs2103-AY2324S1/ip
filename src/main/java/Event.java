/**
 * Event class
 */
public class Event extends Task {
    // Attribute
    private String start;
    private String end;

    // Constructor

    /**
     * The constructor of Event class
     * 
     * @param name name of the event
     * @param start the start of the event
     * @param end the end of the event
     */
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Method to return the string representation of event
     * 
     * @return the string representation of event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + " )";
    }
}
