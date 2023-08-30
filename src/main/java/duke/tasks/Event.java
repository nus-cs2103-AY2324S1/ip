package duke.tasks;

import java.time.LocalDate;

/**
 * Encapsulates an Event task. Event tasks have a start time, end time and description.
 */
public class Event extends Task {

    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructor for a new event task object.
     *
     * @param startDate   starting time of event
     * @param endDate     ending time of event
     * @param description task description of event
     */
    public Event(LocalDate startDate, LocalDate endDate, String description) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the string representation of a event task with its status.
     *
     * @return String representation of event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }

    /**
     * Returns the string representation of the event task to be stored.
     *
     * @return String representing event task to be stored.
     */
    @Override
    public String tasktoString() {
        return "E | " + super.tasktoString() + " | " + this.startDate + " | " + this.endDate;
    }

}
