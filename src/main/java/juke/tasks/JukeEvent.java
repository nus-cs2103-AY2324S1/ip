package juke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import juke.exceptions.JukeStateException;

/**
 * Represents an Event task. Event tasks contain both a startTime and endTime
 * deadline, which are represented by {@code LocalDateTime} objects.
 */
public class JukeEvent extends JukeTask {
    /** String which represents the Task Identifier. */
    private static final String TASK_DESCRIPTOR = "[E] ";

    /** Start Time. */
    private final LocalDateTime startTime;

    /** End Time. */
    private final LocalDateTime endTime;

    /**
     * Creates an instance of {@code JukeEvent}.
     *
     * @param taskName Task description
     * @param startTime Start date/time
     * @param endTime End date/time
     */
    public JukeEvent(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates an instance of {@code JukeEvent}.
     *
     * @param taskName Task description
     * @param startTime Start date/time
     * @param endTime End date/time
     * @param isCompleted Status of completion of the task
     * @throws JukeStateException if the task is already completed
     */
    public JukeEvent(String taskName, LocalDateTime startTime, LocalDateTime endTime, boolean isCompleted) {
        this(taskName, startTime, endTime);

        if (isCompleted) {
            // the event should have a start date that is before the end date
            assert startTime.isBefore(endTime);
            this.setAsComplete();
        }
    }

    /**
     * Returns the string which represents this object when it is saved into the datafile.
     *
     * @return Datafile representation of this object
     */
    @Override
    public String save() {
        return "E" + super.save() + "|" + startTime + "|" + endTime;
    }

    /**
     * Returns String representation of this {@code JukeEvent} object
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return JukeEvent.TASK_DESCRIPTOR
                + super.toString()
                + " (from " + startTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm"))
                + " hrs to " + endTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")) + " hrs)";
    }
}
