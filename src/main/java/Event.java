import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dukeExceptions.MissingEndTimeException;
import dukeExceptions.MissingInformationException;
import dukeExceptions.MissingStartTimeException;

public class Event extends Task {
    protected boolean isDone;
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public static Event of(String input) throws MissingInformationException {
        Matcher matcher = Pattern.compile("event ").matcher(input);
        if (!matcher.find()) {
            // return error: not valid event
        }
        String info = input.substring(matcher.end()).trim();
        matcher = Pattern.compile(" /from ").matcher(info);
        if (!matcher.find()) {
            // return error: not valid event, no start date provided
            throw new MissingStartTimeException();
        }
        String description = info.substring(0, matcher.start()).trim();
        String tmp = info.substring(matcher.end()).trim();
        matcher = Pattern.compile(" /to ").matcher(tmp);
        if (!matcher.find()) {
            // return error: not valid event, no end date provided
            throw new MissingEndTimeException();
        }
        String start = tmp.substring(0, matcher.start()).trim();
        String end = tmp.substring(matcher.end()).trim();
        return new Event(description, start, end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
