package juke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import juke.exceptions.JukeStateException;

/**
 * Represents an Event task. Event tasks contain both a start and end
 * deadline, which are represented by {@code LocalDateTime} objects.
 */
public class JukeEvent extends JukeTask {
    /** String which represents the Task Identifier. */
    private static final String TASK_DESCRIPTOR = "[E] ";

    /** Start Time. */
    private final LocalDateTime start;

    /** End Time. */
    private final LocalDateTime end;

    /**
     * Constructor used to create an event.
     *
     * @param taskName Task description
     * @param start Start date/time
     * @param end End date/time
     */
    public JukeEvent(String taskName, LocalDateTime start, LocalDateTime end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    /**
     * Overloaded constructor used to create an event.
     * @param taskName Task descriptioni
     * @param start Start date/time
     * @param end End date/time
     * @param completion Status of completion of the task
     * @throws JukeStateException if the task is already completed
     */
    public JukeEvent(String taskName, LocalDateTime start, LocalDateTime end, boolean completion) {
        this(taskName, start, end);

        if (completion) {
            this.markAsComplete();
        }
    }

    /**
     * Returns the string which represents this object when it is saved into the datafile.
     * @return Datafile representation of this object
     */
    @Override
    public String save() {
        return "E" + super.save() + "|" + start + "|" + end;
    }

    /**
     * String representation of this {@code JukeEvent} object
     * @return String representation
     */
    @Override
    public String toString() {
        return JukeEvent.TASK_DESCRIPTOR + super.toString()
                + " (from " + start.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm"))
                + " hrs to " + end.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")) + " hrs)";
    }
}
