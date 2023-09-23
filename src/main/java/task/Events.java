package task;

/**
 * Object class for instances of event tasks
 */
public class Events extends Task {
    protected String from;
    protected String to;

    /**
     * Public constructor of the class
     * @param description description of the task
     * @param from start date
     * @param to end date
     */
    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Overloaded constructor of the class for a known status
     * @param status status of the task
     * @param description description of the task
     * @param from start date
     * @param to end date
     */
    public Events(boolean status, String description, String from, String to) {
        super(status, description);
        this.from = from;
        this.to = to;
    }

    /**
     * Overriden toString implementation for a event task
     * @return String representing a event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * formats the properties of the object into a single string line for saving
     * @return String of formatted properties
     */
    @Override
    public String dataFormat() {
        return "event/"
                + super.isDone.toString()
                + "/" + super.description + "/"
                + this.from + "/"
                + this.to;
    }
}
