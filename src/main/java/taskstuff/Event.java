package taskstuff;

import java.time.LocalDateTime;

import duke.Parser;

/**
 * A class which inherits from task.Task class.
 * Represents a task with deadline and start time.
 */
public class Event extends Task {

    /** The starting time of this event. */
    private LocalDateTime startTime;

    /** The ending time of this event. */
    private LocalDateTime endTime;

    /**
     * Initialises using the given description,start time and end time.
     *
     * @param description The name of this event.
     * @param startTime The start time of this event.
     * @param endTime The endt time of this event.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }


    /**
     * Returns a string representation of this event.
     *
     * @return Returns a string describing this event.
     */
    @Override
    public String toString() {

        return "[E]" + super.toString() + " (from: " + startTime.format(Parser.outputFormat) +
                " to: " + endTime.format(Parser.outputFormat) + ")";
    }
}
