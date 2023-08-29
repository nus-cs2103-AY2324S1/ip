package duke.task;

import java.time.LocalDateTime;

/**
 * Represents the event task.
 */
public class Event extends Task {
    /** Start date and time of the event. */
    protected LocalDateTime startTime;
    /** End date and time of the event.  */
    protected LocalDateTime endTime;

    /**
     * Constructor for the event task if the start and end times are represented in a string.
     * 
     * @param description Description of the event task.
     * @param startTime Start date and time of the event.
     * @param endTime End date and time of the event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = this.convertToDateTime(startTime);
        this.endTime = this.convertToDateTime(endTime);
    }

    /**
     * Constructor for the event task when start and end times are properly represented.
     * 
     * @param description Description of the event task.
     * @param startTime Start date and time of the event.
     * @param endTime End date and time of the event.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the start date and time. 
     * 
     * @return Start date and time of the event.
     */
    public String displayStartTime() {
        return this.displayTime(startTime);
    }

    /**
     *  Returns a string representation of the start date and time to be stored.
     * 
     * @return Start date and time of the event in a storeable format.
     */
    public String saveStartTime() {
        return this.saveTime(this.startTime);
    }

    /**
     * Returns a string representation of the end date and time.
     * 
     * @return End date and time of the event.
     */
    public String displayEndTime() {
        return this.displayTime(this.endTime);
    }

    /**
     * Returns a string representation of the end date and time to be stored.
     * 
     * @return End date and time of the event in a storeable format.
     */
    public String saveEndTime() {
        return this.saveTime(this.endTime);
    }

    @Override
    public String getOutputString() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, 
        this.saveStartTime().concat(" to " + this.saveEndTime()));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.displayStartTime() + " to: " 
        + this.displayEndTime() + ")";
    }
}
