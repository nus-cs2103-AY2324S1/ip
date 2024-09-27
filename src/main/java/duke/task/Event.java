package duke.task;

/**
 * Represents task with a starting and ending time that user is keeping track of.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for the class Event.
     * @param description Description of the task with a starting and ending time.
     * @param from The starting time of the class.
     * @param to The ending time of the class.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the task with a starting and ending time as a String formatted to be saved.
     * @return The task with a starting and ending time as a String formatted to be saved.
     */
    @Override
    public String fileString() {
        return "E | " + super.fileDescription() + " | " + this.from + " | " + this.to;
    }

    /**
     * Returns the task with a starting and ending time as a String.
     * @return The task with a starting and ending time as a String.
     */
    public String toString() {
        return "[E]" + super.taskDescription() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
