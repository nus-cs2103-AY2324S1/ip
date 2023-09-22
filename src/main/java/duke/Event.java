package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class for events that has start and end date or time.
 */
public class Event extends Task {
    private String beginString;
    private String transformedBeginString;
    private String endString;
    private String transformedEndString;

    /**
     * Constructor for event class
     * @param description name of the event
     * @param beginString start date or time
     * @param endString end date or time
     */
    public Event(String description, String beginString, String endString) {
        super(description);
        this.beginString = beginString;
        this.endString = endString;

        String[] parsedFrom = beginString.split("\\s+");
        String[] parsedTo = endString.split("\\s+");
        String fromDate = LocalDate.parse(parsedFrom[0]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        assert (fromDate != null);
        String toDate = LocalDate.parse(parsedTo[0]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        assert (toDate != null);

        this.transformedBeginString = parsedFrom.length > 1 ? fromDate + " " + parsedFrom[1] : fromDate;
        this.transformedEndString = parsedTo.length > 1 ? toDate + " " + parsedTo[1] : toDate;
    }

    /**
     * The formatted string endString be printed in terminal
     * @return the formatted string that included names and date or time
     */
    @Override
    public String toString() {
        String first = "[E]" + "[" + this.getStatusIcon() + "] " + this.description + " ";
        String second = "(beginString: " + this.transformedBeginString + " endString: " + this.transformedEndString + ")";
        return first + second;
    }

    /**
     * The formatted string endString be printed in file
     * @return a formatted string
     */
    @Override
    public String stringInFile() {
        int status = super.isDone ? 1 : 0;
        return "E | " + status + " | " + this.description + " | " + this.beginString + " | " + this.endString;
    }
}
