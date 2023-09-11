package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of type Event.
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;
    private final DateTimeFormatter formatter;

    /**
     * A constructor for task of type Event.
     *
     * @param description the task details.
     * @param from the start date/time for the task.
     * @param to the end date/time for the task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
        this.formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        assert this.from != null : "from should not be null";
        assert this.to != null : "to should not be null";
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(formatter) + " to: "
                + this.to.format(formatter) + ")";
    }

    /**
     * Returns the string representation of the task in file format.
     *
     * @return the string representation in file format.
     */
    @Override
    public String toFileFormat() {
        return "E | " + this.isDone + " | " + this.description + " | " + this.from.format(formatter)
                + " | " + this.to.format(formatter);
    }

    /**
     * Returns the task type.
     *
     * @return the task type.
     */
    @Override
    public String getTaskType() {
        return "event";
    }

    @Override
    public void updateTask(String description) throws DukeException {
        throw new DukeException(ExceptionTypes.INCOMPLETEUPDATEDETAILS);
    }

    @Override
    public void updateTask(String description, LocalDateTime by) throws DukeException {
        throw new DukeException(ExceptionTypes.INCOMPLETEUPDATEDETAILS);
    }

    /**
     * Updates the event task.
     *
     * @param description the task details.
     * @param from the start date/time for the task.
     * @param to the end date/time for the task.
     */
    @Override
    public void updateTask(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }
}
