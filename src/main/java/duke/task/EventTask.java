package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.DukeException;
import duke.enums.TaskType;

/**
 * Represents a EventTask class that deals with the event task that has a from and to date attached to it.
 */
public class EventTask extends Task {

    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructor for duke.task.Task.
     *
     * @param description Description of the task.
     */
    public EventTask(String description, LocalDate from, LocalDate to) throws DukeException {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the from date of the task.
     *
     * @return String representation from date of the task in a string format MMM dd yyyy.
     */
    public String getFrom() {
        // Format in MMM dd yyyy
        return this.from.format(DateTimeFormatter.ofPattern(Task.DATE_FORMAT));
    }

    /**
     * Returns the to date of the task.
     *
     * @return String representation to date of the task in a string format MMM dd yyyy.
     */
    public String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern(Task.DATE_FORMAT));
    }

    @Override
    public String getFileDescriptor() {
        return super.getFileDescriptor()
                + String.format("| %s | %s | %s", this.from.toString(), this.to.toString(), TaskType.EVENT);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.getFrom() + " to: " + this.getTo() + ")";
    }
}
