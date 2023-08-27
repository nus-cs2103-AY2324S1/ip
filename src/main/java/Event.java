public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Class constructor specifying the description of the event task.
     * @param description the string description of the event
     * @param from the string description of time to begin the event
     * @param to the string description of time to end the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string description which contains the starting and ending time of the event.
     * @return a string representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    public String saveTask() {
        return "E|" + (this.isDone ? "X|" : " |") + this.description + "|" + this.from + "|" + this.to;
    }

}