package jeeves.task;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    /**
     * Getter method for the Event start time
     *
     * @return Starting time of the task
     */
    public String getStartTime() {
        return from;
    }

    /**
     * Getter method for the Event end time
     *
     * @return Ending time of the task
     */
    public String getEndTime() {
        return to;
    }

    @Override
    public String toString() {
        return String.format(this.id + ". [E]" + super.toString() + " (from: " + from + " to: " + to + ")");
    }
}
