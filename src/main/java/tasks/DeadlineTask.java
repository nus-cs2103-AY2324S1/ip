package tasks;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import exceptions.DukeException;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {

    /** Datetime of deadline of task */
    protected LocalDateTime by;

    /**
     * Constructor for DeadlineTask.
     * @param description
     * @param by
     * @throws DukeException
     */
    public DeadlineTask(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, Task.DATE_FORMAT);
        } catch (DateTimeException e) {
            throw new DukeException("Date should follow the format d/M/yyyy HHmm");
        }
    }

    /**
     * Constructor for DeadlineTask.
     */
    public DeadlineTask() {
        super("");
    }

    /**
     * Returns the string representation of the deadline task.
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DATE_FORMAT_OUTPUT) + ")";
    }

    /**
     * Returns the string representation of the deadline task to be saved in a file.
     * @return LocalDateTime by
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Sets the deadline of the task.
     * @param by
     */
    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline task to be saved in a file.
     * @return String
     */
    public String toFileString() {
        return "D | " + super.getStatusIcon() + " | " + description + " | " + by.format(DATE_FORMAT);
    }

    /**
     * Sets the deadline task from a string representation of the deadline task in a file.
     * @param fileString
     * @throws DukeException
     */
    public void fromFileString(String fileString) throws DukeException {
        String[] fileStringArray = fileString.split(" \\| ");
        this.setStatusIcon(fileStringArray[1]);
        this.description = fileStringArray[2];
        try {
            this.by = LocalDateTime.parse(fileStringArray[3], Task.DATE_FORMAT);
        } catch (DateTimeException e) {
            throw new DukeException("Date should follow the format d/M/yyyy HHmm");
        }
    }

}
