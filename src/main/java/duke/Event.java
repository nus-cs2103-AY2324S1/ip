package duke;

import duke.Task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Event class for events that has start and end date or time.
 */
public class Event extends Task{
    protected String from;
    //transformed only for printing
    protected String transfromedFrom;
    protected String to;
    protected String transformedTo;

    /**
     * Constructor for event class
     * @param description name of the event
     * @param from start date or time
     * @param to end date or time
     */
    public Event (String description, String from, String to) {
        //no extra information for todolist
        super(description);
        this.from = from;
        this.to = to;
        String parsed_from[] = from.split("\\s+");
        String parsed_to[] = to.split("\\s+");
        //assume that date is always entered first
        //only consider with or without time
        String fromDate = LocalDate.parse(parsed_from[0]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String toDate = LocalDate.parse(parsed_to[0]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.transfromedFrom= parsed_from.length > 1 ? fromDate + " " + parsed_from[1] : fromDate;
        this.transformedTo = parsed_to.length > 1 ? toDate+ " " + parsed_to[1] : toDate;
    }

    /**
     * The formatted string to be printed in terminal
     * @return the formated string that included names and date or time
     */
    @Override
    public String toString() {
        String first = "[E]" + "[" + this.getStatusIcon() + "] " + this.description + " ";
        String second = "(from: " + this.transfromedFrom + " to: " + this.transformedTo + ")";
        return first + second;
    }

    /**
     * The formatted string to be printed in file
     * @return a formatted string
     */
    @Override
    public String stringInFile() {
        int status = super.isDone ? 1 : 0;
        return "E | " + status + " | " + this.description + "| " + this.from + "| " + this.to;
    }
}
