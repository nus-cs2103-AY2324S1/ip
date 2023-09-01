package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exception.DukeException;
import duke.exception.MissingEndTimeException;
import duke.exception.MissingInformationException;
import duke.exception.MissingStartTimeException;


/**
 * A class that represents the Event class. It is 
 * a subtype of the abstract Task class.
 */

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * A constructor of the Event object
     * @param description Description of Event object
     * @param start start date of Event object
     * @param end end date of Event object
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Factory method of Event class.
     * String input is guaranteed to start with "event"
     * @param input user input from terminal.
     * @return a Event object given the input string.
     * @throws MissingInformationException due to possibly an empty description of the 
     * Event task or a missing /from start time or a missing /to end time for the task.
     */
    public static Event of(String input) throws DukeException {
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

        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return new Event(description, startDate, endDate);

    }

    /**
     * A method that returns the string representation of an Event object.
     * @return string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    
    /**
     * A method that returns the string representation of a Deadline object
     * @return string representation of the Deadline object to be stored in the database
     */
    @Override
    public String toBeStored() {
        String marked = this.isDone() ? "1" : "0";
        return "E | " + marked + " | " + this.getDescription() + " | " + this.start + " | " + this.end + "\n";
    }
}
