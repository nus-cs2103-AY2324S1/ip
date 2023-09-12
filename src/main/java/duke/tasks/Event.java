package duke.tasks;

/**
 * Special type of task that has a description, 'from' date and 'to' date.
 */
class Event extends Task {
    protected String type = "E";
    protected String timeline;

    /**
     * Constructor for Event class.
     * @param description Describes the event
     * @param timeline Timeline of the event
     */
    public Event(String description, String timeline) {
        super(description);
        this.timeline = timeline;
    }
    @Override
    public void mark() {
        this.isDone = true;
    }
    @Override
    public void unmark() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return this.type + " | " + this.getStatusIcon() + " | "
                + this.description + " | " + this.timeline;
    }
}
