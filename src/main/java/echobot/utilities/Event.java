package echobot.utilities;

/**
 * Class to declare a Event task
 */
public class Event extends Task {

    /**
     * Creates a new instance of an event task
     *
     * @param name Name of task
     * @param startDate Start time of task
     * @param endDate End time of task
     */
    public Event(String name, String startDate, String endDate) {
        super(name, Type.EVENT, "(from: " + startDate + " to: " + endDate + ")");
    }
}
