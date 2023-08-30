public class Event extends Task {
    /**
     * the type Icon
     */
    private String type = "E";
    /**
     * start of the event
     */
    private String from = "";
    /**
     * end of the event
     */
    private String to = "";

    /**
     * constructor for Event task
     * @param description the text stored
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * override the toString method
     * @return a string
     */
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toDataString() {
        return this.type + " / " + super.toDataString() + " / " + this.from + " / " + this.to;
    }
}
