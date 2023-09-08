package seedu.duke.task;

import seedu.duke.task.Task;

import java.time.LocalDateTime;

/**
 * Represents an Event task.
 * This class is inherited from the Task class.
 * An Event task always have a description,
 * a starting time and an ending time.
 *
 * @author KAM JIA YUE
 * @since 2023-08-29
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * The main constructor of this Event task.
     *
     * @param description Description of this Event task.
     * @param from Starting time of this Event task.
     * @param to Ending time of this Event task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = super.parseStringToTime(from);
        this.to = super.parseStringToTime(to);
    }

    /**
     * Returns a string representation of this
     * Event task when it is saved in the
     * storage.
     *
     * @return a string representation of this Event task for saving.
     */
    public String toStringForSave() {
        return "E" + " | " + super.toStringForSave() + " | "
                + super.convertTimeForSave(this.from) + " | "
                + super.convertTimeForSave(this.to);
    }

    /**
     * {@inheritDoc}
     *
     * Returns a string representation of this
     * Event task when it is printed.
     *
     * @return a string representation of this Event task for printing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + super.convertTimeToString(this.from)
                + " to: " + super.convertTimeToString(this.to) + ")";
    }
}
