package task;

public class Event extends Task {

    /** start date type String */
    protected String from;
    /** end date type String */
    protected String to;

    /**
     * Initialize task.Task object with task name and task is not done.
     *
     * @param description type String;
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String fileFormat() {
        return ("E|" + super.fileFormat() + "|" + this.from + "|" + this.to);
    }
}
