import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dukeExceptions.MissingEndTimeException;
import dukeExceptions.MissingInformationException;
import dukeExceptions.MissingStartTimeException;

/*
 * A class that is represents the Event class. It is 
 * a subtype of the abstract Task class.
 */

public class Event extends Task {
    protected boolean isDone;
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /*
     * Factory method of Event class.
     * String input is guaranteed to start with "event"
     * 
     * @param input user input from terminal.
     * 
     * @return a Event object given the input string.
     * 
     * @throws MissingInformationException due to possibly an empty
     * description of the Event task or a missing /from start time or a
     * missing /to end time for the task.
     */
    public static Event of(String input) throws MissingInformationException {
        Matcher matcher = Pattern.compile("event ").matcher(input);
        matcher.find();
        String info = input.substring(matcher.end()).trim();
        matcher = Pattern.compile(" /from ").matcher(info);
        if (!matcher.find()) {
            throw new MissingStartTimeException();
        }
        String description = info.substring(0, matcher.start()).trim();
        String tmp = info.substring(matcher.end()).trim();
        matcher = Pattern.compile(" /to ").matcher(tmp);
        if (!matcher.find()) {
            throw new MissingEndTimeException();
        }
        String start = tmp.substring(0, matcher.start()).trim();
        String end = tmp.substring(matcher.end()).trim();
        return new Event(description, start, end);
    }

    /*
     * A method that returns the string representation of an Event object.
     * 
     * @return string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
