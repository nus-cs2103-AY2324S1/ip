/**
 * Represents a task that starts at a specific date/time and ends at a specific date/time.
 * This class is a subclass of the Task class and inherits its properties and methods.
 */
public class Event extends Task {

    /**
     * The duration of the event starts at a specific date/time and ends at a specific date/time.
     */
    protected String duration;

    /**
     * Constructs an Event object with the given description and duration.
     *
     * @param description The description of the event.
     * @param duration The duration of the event.
     */
    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Returns output string representation of the Event task.
     *
     * @return The output string representation of the Event task.
     */
    @Override
    public String outputMsg() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + this.duration;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return The string representation of the Event task with description and duration.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + this.duration + ")";
    }
}
