package duke.task;
import duke.exception.DukeException;
import duke.exception.InvalidTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the Deadline of a task.
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Constructs a deadline with a specified description and date.
     *
     * @param description A string describing the deadline task.
     * @throws DukeException If the deadline date is not a valid date.
     */
    public Deadline(String description) throws DukeException {
        super(description.substring(0, description.indexOf("/by") - 1));
        int byIndex = description.indexOf("/by");
        try{
            this.deadline = LocalDate.parse(description.substring(byIndex + 4));
        }
        catch (DateTimeParseException e) {
            throw new InvalidTimeException("Invalid input of Date");
        }
    }

    /**
     * Returns a String representing the Deadline that will be stored.
     *
     * @return the string representing the Deadline that will be stored.
     */
    @Override
    public String getInput() {
        return "D | " + this.getStatusIcon() + " | " + this.description + " | " + this.deadline;
    }

    /**
     * Returns the String representation of a Deadline.
     *
     * @return the String representation of a Deadline.
     */
    @Override
    public String toString() {
        return "[D] [" + this.getStatusIcon() + "] " + this.description + " (by: " +
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
