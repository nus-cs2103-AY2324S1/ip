package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;
import duke.util.Storage;


/**
 * Represents the event task.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for event task.
     * @param description Description of the event
     * @param from Start time of the event
     * @param to End time of the event
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);

            if (this.from.isAfter(this.to)) {
                throw new DukeException("Start date cannot be after end date");
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Updates the from and to time of Event.
     * @param from New from time.
     * @param to New to time.
     */
    public void updateTime(String from, String to) {
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String reschedule(String rescheduleDetails, Storage storage) throws DukeException {
        String[] newTime = rescheduleDetails.split("/from | /to ");
        if (newTime.length != 3) {
            throw new DukeException("Valid Input Syntax: reschedule {taskNumber} /from {newTime} /to {newTime}");
        }
        updateTime(newTime[1], newTime[2]);
        storage.saveTask(description, newTime[1], newTime[2]);
        return "Rescheduled Task: " + this;
    }

    @Override
    public String toString() {
        assert (from != null) : "From time of event task cannot be empty.";
        String fromFormatted = from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        assert (to != null) : "To time of event task cannot be empty.";
        String toFormatted = to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return "[E]" + super.toString() + " (from: " + fromFormatted + " to: " + toFormatted + ")";
    }

}
