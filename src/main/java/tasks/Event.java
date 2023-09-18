package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The class representing an Event task.
 *
 * @author Gallen Ong
 */
public class Event extends Task {
    protected LocalDate fromDate;
    protected LocalDate toDate;

    /**
     * Initialises an Event task with a description, start and end time.
     *
     * @param description The task description.
     * @param from The task start time.
     * @param to The task end time.
     */

    public Event(String description, String from, String to) {
        super(description);
        this.fromDate = LocalDate.parse(from);
        this.toDate = LocalDate.parse(to);
    }

    /**
     * Initialises an Event task by retrieving from existing file.
     *
     * @param status The task status retrieved.
     * @param description The task description retrieved.
     * @param from The task start time retrieved.
     * @param to The task end time retrieved.
     */
    public Event(String status, String description, String from, String to) {
        super(description);
        this.fromDate = LocalDate.parse(from);
        this.toDate = LocalDate.parse(to);
        if (status.equals("1")) {
            this.isDone = true;
        }
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return The Event task in string format.
     */
    @Override
    public String toString() {
        String strFrom = this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String strTo = this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[E]" + super.toString() + " (from: " + strFrom + " to: " + strTo + ")";
    }

    /**
     * Returns a string representation of the Event task to be added to a file.
     *
     * @return The Event task in string format, specific for file operations.
     */
    @Override
    public String toStringForFile() {
        return "E | " + super.toStringForFile() + " | " + this.fromDate + " | " + this.toDate;
    }
}
