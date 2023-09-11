package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class for events that has start and end date or time.
 */
public class Event extends Task {
    protected String from;
    //transformed only for printing
    protected String transformedFrom;
    protected String to;
    protected String transformedTo;

    /**
     * Constructor for event class
     * @param description name of the event
     * @param from start date or time
     * @param to end date or time
     */
    public Event(String description, String from, String to) {
        //no extra information for todolist
        super(description);
        this.from = from;
        this.to = to;
        String[] parsedFrom = from.split("\\s+");
        String[] parsedTo = to.split("\\s+");
        //assume that date is always entered first
        //only consider with or without time
        String fromDate = LocalDate.parse(parsedFrom[0]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        assert (fromDate != null);
        String toDate = LocalDate.parse(parsedTo[0]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        assert (toDate != null);
        this.transformedFrom = parsedFrom.length > 1 ? fromDate + " " + parsedFrom[1] : fromDate;
        this.transformedTo = parsedTo.length > 1 ? toDate + " " + parsedTo[1] : toDate;
    }

    /**
     * The formatted string to be printed in terminal
     * @return the formatted string that included names and date or time
     */
    @Override
    public String toString() {
        String first = "[E]" + "[" + this.getStatusIcon() + "] " + this.description + " ";
        String second = "(from: " + this.transformedFrom + " to: " + this.transformedTo + ")";
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
