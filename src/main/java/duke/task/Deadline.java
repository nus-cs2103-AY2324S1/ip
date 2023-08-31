package duke.task;

import duke.parser.Parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The Deadline class represents a task with a description,
 * completion status, and a deadline date and time.
 */
public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructs a Deadline object.
     *
     * @param description Description of the task.
     * @param date LocalDate representation of the date of deadline task.
     * @param time LocalTime representation of the time of deadline task.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * Constructs the Deadline object.
     *
     * @param description Description of the task.
     * @param date String representation of the date of deadline task.
     * @param time String representation of the time of deadline task.
     */
    public Deadline(String description, String date, String time) {
        super(description);
        this.date = Parser.parseDate(date);
        this.time = Parser.parseTime(time);
    }

    /**
     * Returns a formatted string representation of the deadline date.
     *
     * @return A string representing the deadline date in the format "d MMM yyyy".
     */
    public String reformatDate() {
        return this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    /**
     * Returns a formatted string representation of the deadline time.
     *
     * @return A string representing the deadline time in the format "h.mma".
     */
    public String reformatTime() {
        return this.time.format(DateTimeFormatter.ofPattern("h.mma", Locale.US));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + reformatDate() + " " + reformatTime() + ")";
    }

    @Override
    public String toFileFormat() {
        return "D" + super.toFileFormat() + " | " + reformatDate() + " | " + reformatTime() + "\n";
    }
}
