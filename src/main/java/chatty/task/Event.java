package chatty.task;

/**
 * A class that represents an Event task.
 */
public class Event extends Task {

    private final String startTime;
    private final String endTime;

    /**
     * Constructor for the Event class that calls the constructor of the parent class to create a Task object.
     *
     * @param taskDescription The event task to be added.
     * @param startTime       The start time of the event task.
     * @param endTime         The end time of the event task.
     */
    public Event(String taskDescription, String startTime, String endTime) {
        super(taskDescription);
        assert startTime != null : "Start time should not be null when creating an Event.";
        assert endTime != null : "End time should not be null when creating an Event.";
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the String representation of the object.
     *
     * @return The String representation of the Event task.
     */
    @Override
    public String toString() {
        String status = (this.isDone) ? "[E][X] " : "[E][ ] ";
        String taskString = String.format("%s (from: %s to: %s)", this.task, this.startTime, this.endTime);
        return status + taskString;
    }
}
