import java.util.regex.*;

import dukeExceptions.MissingDeadlineException;
import dukeExceptions.MissingInformationException;

public class Deadline extends Task {
    protected boolean isDone;
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public static Deadline of(String input) throws MissingInformationException {
        Matcher matcher = Pattern.compile("deadline ").matcher(input);
        if (!matcher.find()) {
            // return error: not valid deadline
        }
        String info = input.substring(matcher.end()).trim();
        matcher = Pattern.compile(" /by ").matcher(info);
        if (!matcher.find()) {
            // return error: not valid deadline, no end date provided
            throw new MissingDeadlineException();
        }
        String description = info.substring(0, matcher.start()).trim();
        String deadline = info.substring(matcher.end()).trim();
        return new Deadline(description, deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
