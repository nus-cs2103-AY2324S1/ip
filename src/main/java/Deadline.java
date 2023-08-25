import java.util.regex.*;

import dukeExceptions.MissingDeadlineException;
import dukeExceptions.MissingInformationException;

/*
 * A class that is represents the Deadline class. It is 
 * a subtype of the abstract Task class.
 */

public class Deadline extends Task {
    protected boolean isDone;
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /*
     * Factory method of Deadline class.
     * String input is guaranteed to start with "deadline"
     * 
     * @param input user input from terminal.
     * 
     * @return a Deadline object given the input string.
     * 
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
        return new Deadline(description, deadline);
    }

    /*
     * A method that returns the string representation of a Deadline object.
     * 
     * @return string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
