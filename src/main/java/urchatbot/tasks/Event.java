package urchatbot.tasks;

public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Construct the Event class.
     *
     * @param description Description of the task.
     * @param isDone If the task is done.
     * @param from Starting date and/or time of the event.
     * @param to Ending date and/or time of the event.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        if (this.isDone) {
            return "E | 1 | " + description
                    + " | " + from + "-" + to;
        } else {
            return "E | 0 | " + description
                    + " | " + from + "-" + to;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}