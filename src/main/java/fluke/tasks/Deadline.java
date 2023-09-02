package fluke.tasks;

import fluke.exceptions.FlukeException;
import fluke.exceptions.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * A deadline task refers to a task which has a deadline.
 */
public class Deadline extends Task {
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);

    /**
     * The deadline of the task.
     */
    protected LocalDate byDate;

    /**
     * Constructs a Deadline. Takes in a description and a by date.
     * @param description Description of the task.
     * @param byDate Date which the task is due, in YYYY-MM-DD format.
     * @throws FlukeException when the description or by date is invalid.
     */
    public Deadline(String description, String byDate) throws FlukeException {
        super(description);
        try {
            this.byDate = LocalDate.parse(byDate);
        } catch (DateTimeParseException d) {
            throw new InvalidInputException();
        }
    }

    /**
     *
     * Constructs a Deadline. Takes in a description and a by date.
     * @param description Description of the task.
     * @param isDone Whether the deadline is marked as done.
     * @param byDate Date which the task is due, in YYYY-MM-DD format.
     * @throws FlukeException when the description or by date is invalid.
     */
    public Deadline(String description, boolean isDone, String byDate) throws FlukeException {
        super(description, isDone);
        this.byDate = LocalDate.parse(byDate);
    }

    /**
     * String representation of a Deadline.
     * @return a String representation of a Deadline, containing description and date which it is due.
     */
    @Override
    public String toString() {
        String byString = byDate.format(DATE_TIME_FORMATTER);
        return "[D]" + super.toString() + " (by: " + byString + ")";
    }
}
