package duke;

/**
 * Represents an Event. Event inherits from Task and contains a string
 * representation of start and end.
 */
public class Event extends Task {
    private String fromString;
    private String toString;

    /**
     * Constructs an Event object.
     * 
     * @param taskName
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.fromString = from;
        this.toString = to;
    }

    /**
     * Returns a string representation of the Event object.
     * Overrides the toString method in Task.
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromString + " to: " + this.toString + ")";
    }

    /**
     * Returns a string representation of the Event object to be saved in the file.
     * Overrides the saveString method in Task.
     * @return String representation of the Event object to be saved in the file.
     */
    @Override
    public String saveString() {
        return "E | " + (this.getIsDone() ? "1" : "0") + " | " + this.getTaskName() + " | " 
                + this.fromString + " | " + this.toString;
    }
}
