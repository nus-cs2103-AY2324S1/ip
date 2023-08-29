package tasks;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import exceptions.DukeException;

/**
 * Represents an event task.
 */
public class EventTask extends Task {

    /** Date time of start of task */
    protected LocalDateTime startDate;

    /** Date time of end of task */
    protected LocalDateTime endDate;

    /**
     * Constructor for EventTask.
     * @param description
     * @param startDate
     * @param endDate
     * @throws DukeException
     */
    public EventTask(String description, String startDate, String endDate) throws DukeException {
        super(description);
        try {
            this.startDate = LocalDateTime.parse(startDate, Task.DATE_FORMAT);
            this.endDate = LocalDateTime.parse(endDate, Task.DATE_FORMAT);
        } catch (DateTimeException e) {
            throw new DukeException("Date should follow the format d/M/yyyy HHmm");
        }
    }

    /**
     * Constructor for EventTask.
     */
    public EventTask() {
        super("");
    }

    /**
     * Returns the string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate.format(Task.DATE_FORMAT_OUTPUT) + " to: " + endDate.format(Task.DATE_FORMAT_OUTPUT) + ")";
    }

    /**
     * Returns the string representation of the event task to be saved in a file.
     * @return LocalDateTime startDate
     */
    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    /**
     * Returns the string representation of the event task to be saved in a file.
     * @return LocalDateTime endDate
     */
    public LocalDateTime getEndDate() {
        return this.endDate;
    }
    
    /**
     * Convert the event task to a string to be saved in a file.
     * @return String
     */
    public String toFileString() {
        return "E | "  + super.getStatusIcon() + " | " + description + " | " + startDate.format(Task.DATE_FORMAT) + " | " + endDate.format(Task.DATE_FORMAT);
    }

    /**
     * Convert from a string to a event task.
     * @param fileString
     * @throws DukeException
     */
    public void fromFileString(String fileString) throws DukeException {
        String[] fileStringArray = fileString.split(" \\| ");
        this.setStatusIcon(fileStringArray[1]);
        this.description = fileStringArray[2];
        try {
            this.startDate = LocalDateTime.parse(fileStringArray[3], Task.DATE_FORMAT);
            this.endDate = LocalDateTime.parse(fileStringArray[4], Task.DATE_FORMAT);
        } catch (DateTimeException e) {
            throw new DukeException("Date should follow the format d/M/yyyy HHmm");
        }
    }
}
