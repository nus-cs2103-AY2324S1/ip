public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Used to evaluate String form of a Event
<<<<<<< HEAD
     * @return String form of an Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + "to: " + to.trim() + ")";
=======
     * @return String form of an Eventgit
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to.trim() + ")";
>>>>>>> branch-Level-7
    }
}
