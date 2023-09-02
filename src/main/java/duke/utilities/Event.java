package duke.utilities;

/**
 * Class to declare a Event task
 */
public class Event extends Task {
	
    /** Start time of the task */
    private String start;

    /** End time of the task */
    private String end;

    /**
     * Creates a new instance of an event task
     *
     * @param name Name of task
     * @param start Start time of task
     * @param end End time of task
     */
    public Event(String name, String start, String end) {
        super(name, Type.EVENT, " (from: " + start + " to: " + end + ")");
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }
}
