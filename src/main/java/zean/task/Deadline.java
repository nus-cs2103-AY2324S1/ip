package zean.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import zean.exception.ZeanException;

/**
 * The class representing a deadline task.
 *
 * @author Zhong Han
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructor for the deadline task.
     *
     * @param bool The completion status of the deadline task.
     * @param description The description of the deadline task.
     * @param by The due date of the deadline task.
     */
    public Deadline(String bool, String description, String by) throws ZeanException {
        super(bool, description.strip());
        assert description != null;
        try {
            this.by = LocalDate.parse(by.strip());
        } catch (DateTimeParseException e) {
            throw new ZeanException("The date is invalid!");
        }
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return a string comprising the symbol, status,
     *      description and due date of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDeadline() + ")";
    }

    /**
     * Returns the string representing the task to be written in the disk.
     *
     * @return The string describing this task to be written in the disk.
     */
    @Override
    public String toStringForFile() {
        return "D | " + super.toStringForFile() + " | " + this.by;
    }

    /**
     * Returns the string with formatted due date of the deadline task.
     *
     * @return The string with formatted due date of the deadline task.
     */
    protected String getDeadline() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
