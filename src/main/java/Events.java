import java.io.Serializable;

/**
 * This class is used to create Event objects that holds the description and when the event starts and ends
 */
public class Events extends  Task implements Serializable {
    /**
     *  This variable holds the information on when this instance of an event object starts
     */
    protected String from;
    /**
     *  This variable holds the information on when this instance of an event object ends
     */
    protected String to;

    /**
     * this is a constructor to create an instance of an Event object
     * @param text This param gives the description
     * @param from This param gives the information on when the event starts
     * @param to This param gives the information on when the event ends
     */
    public Events(String text, String from, String to){
        super(text);
        this.from = from;
        this.to = to;
    }
    /**
     * This is a toString method that has been Override to better suit the display of this class
     * @return The string form and information about this event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
