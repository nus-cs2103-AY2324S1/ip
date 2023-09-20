package duke.tasks;

import java.time.LocalDateTime;

import duke.Parser;

/**
 * Represents an Event task object.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event object with given description and start/end time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Updates description of task
     *
     * @param newDescription New description.
     */
    @Override
    public void updateTaskDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Updates a date field of task.
     *
     * @param fieldToUpdate Field that is to be updated.
     * @param newDate New date.
     */
    @Override
    public void updateDate(String fieldToUpdate, LocalDateTime newDate) {
        if (fieldToUpdate.equals("from")) {
            this.from = newDate;
        } else {
            this.to = newDate;
        }
    }

    /**
     * Represents Event task in string format.
     *
     * @return String representation of Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.formatDateTime(from) + " to: "
                + Parser.formatDateTime(to) + ")";
    }

    /**
     * Represents Event task in string format to be stored.
     *
     * @return String representation of Event task.
     */
    @Override
    public String toStorageFormat() {
        return ("E" + super.toStorageFormat() + " | " + from + " | " + to);
    }
}
