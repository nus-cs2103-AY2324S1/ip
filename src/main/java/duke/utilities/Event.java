package duke.utilities;

/**
 * Class to declare a Event task
 */
public class Event extends Task {
    /** Start time of the task */
    private String startDate;

    /** End time of the task */
    private String endDate;

    /**
     * Creates a new instance of an event task
     * 
     * @param name Name of task
     * @param startDate Start time of task
     * @param endDate End time of task
     */
    public Event(String name, String startDate, String endDate) {
        super(name, Type.EVENT, " (from: "+ startDate + " to: " + endDate + ")");
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStart() {
        return this.startDate;
    }

    public String getEnd() {
        return this.endDate;
    }
}