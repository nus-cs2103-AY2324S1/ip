package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exception.MissingDeadlineException;
import duke.exception.MissingInformationException;


/**
 * A class that represents the Deadline class. It is 
 * a subtype of the abstract Task class.
 */

public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * A constructor of the Deadline object
     * @param description Description of deadline object
     * @param deadline deadline of Deadline object
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Factory method of Deadline class.
     * String input is guaranteed to start with "deadline"
     * @param input user input from terminal.
     * @return a Deadline object given the input string.
     * @throws MissingInformationException due to possibly an empty
     * description of the Deadline task or a missing /by deadline for
     * the task.
     */
    public static Deadline of(String input) throws MissingInformationException {
        Matcher matcher = Pattern.compile("deadline ").matcher(input);
        matcher.find();
        String info = input.substring(matcher.end()).trim();
        matcher = Pattern.compile(" /by ").matcher(info);
        if (!matcher.find()) {
            throw new MissingDeadlineException();
        }
        String description = info.substring(0, matcher.start()).trim();
        String deadline = info.substring(matcher.end()).trim();
        LocalDate deadlineDate = LocalDate.parse(deadline);
        return new Deadline(description, deadlineDate);
    }

    /**
     * A method that returns the string representation of a Deadline object
     * @return string representation of the Deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * A method that returns the string representation of a Deadline object
     * @return string representation of the Deadline object to be stored in the database
     */
    @Override
    public String toBeStored() {
        String marked = this.isDone() ? "1" : "0";
        return "D | " + marked + " | " + this.getDescription() + " | " + this.deadline + "\n";
    }
}
