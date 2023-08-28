public class Events extends Task{
    protected String from;
    protected String to;

    /**
     * A constructor the public Events class
     * @param description the description of the event.
     * @param from the beginning time of the event.
     * @param to the ending time of the event.
     */
    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String saveTaskString() {
        return "E" + super.saveTaskString() + " | " + this.from + "-" + this.to;
    }
    /**
     * This method converts the value of an Event into a String type.
     * @return the String representation of the event with its type, completion status,
     * beginning time and ending time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
