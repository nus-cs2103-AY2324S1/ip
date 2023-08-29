/**
 * Special type of task that has a description, 'from' date and 'to' date.
 */
class Event extends Task {
    /**
     * Signifies the 'Event' task.
     */
    protected String type = "E";
    /**
     * Starting date of this task.
     */
    protected String from;
    /**
     * Ending date of this task.
     */
    protected String to;

    /**
     * Constructor for Event class.
     * @param description Describes the event
     * @param from Start of the event
     * @param to End of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Mark this Event task as done.
     */
    @Override
    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n"
                + this);
    }

    /**
     * Unmark this Event task as not done yet.
     */
    @Override
    public void unmark() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n"
                + this);
    }

    /**
     * Description of this Event task.
     * @return String containing all the relevant information of this Event task
     */
    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + this.getStatusIcon() + "] "
                + this.description + " (from: " + this.from
                + " to:" + this.to + ")";
    }
}
