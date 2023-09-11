package duke.task;

import duke.exception.DukeException;
import duke.util.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the deadline task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for the deadline task
     * @param description Description of the task
     * @param by duke.task.Deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Update the deadline time.
     * @param by New deadline time.
     */
    public void updateByTime(String by) {
        this.by = LocalDate.parse(by);
    }

    @Override
    public String reschedule(String rescheduleDetails, Storage storage) throws DukeException {
        String[] newByTime = rescheduleDetails.split("/by ");
        if (newByTime.length != 2) {
            throw new DukeException("Valid Input Syntax: reschedule {taskNumber} /by {newTime}");
        }
        updateByTime(newByTime[1]);
        storage.saveTask(description, newByTime[1]);
        return "Rescheduled Task: " + this;
    }

    @Override
    public String toString() {
        assert (by != null) : "By time of deadline task cannot be null.";
        String byFormatted = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + byFormatted + ")";
    }
}
