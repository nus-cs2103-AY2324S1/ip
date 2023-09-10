package tasks;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import exceptions.DukeException;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {

    /** Datetime of deadline of task */
    protected LocalDateTime byDateTime;

    /**
     * Constructor for DeadlineTask.
     * @param description
     * @param byDateTime
     * @throws DukeException
     */
    public DeadlineTask(String description, String byDateTime) throws DukeException {
        super(description);
        try {
            this.byDateTime = LocalDateTime.parse(byDateTime, Task.getDateFormat());
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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byDateTime.format(Task.getDateFormatOutput()) + ")";
    }

    /**
     * Returns the string representation of the deadline task to be saved in a file.
     * @return LocalDateTime byDateTime
     */
    public LocalDateTime getByDateTime() {
        return this.byDateTime;
    }

    /**
     * Sets the deadline of the task.
     * @param by LocalDateTime of deadline
     */
    public void setByDateTime(LocalDateTime by) {
        this.byDateTime = by;
    }

    /**
     * Returns the string representation of the deadline task to be saved in a file.
     * @return String to be saved in a file
     */
    @Override
    public String toFileString() {
        return "D | " + super.getStatusIcon() + " | " + getDescription() + " | " + byDateTime.format(Task.getDateFormat());
    }

    /**
     * Sets the deadline task from a string representation of the deadline task in a file.
     * @param fileString Stored list in String format within the file.
     * @throws DukeException
     */
    @Override
    public void fromFileString(String fileString) throws DukeException {
        String[] fileStringArray = fileString.split(" \\| ");
        setStatusIcon(fileStringArray[1]);
        setDescription(fileStringArray[2]);
        try {
            this.byDateTime = LocalDateTime.parse(fileStringArray[3], Task.getDateFormat());
        } catch (DateTimeException e) {
            throw new DukeException("Date should follow the format d/M/yyyy HHmm");
        }
    }

}
