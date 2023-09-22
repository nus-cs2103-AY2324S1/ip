package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class for task with deadlines.
 */
public class Deadline extends Task {

    protected String dateString;
    protected String transformedDate;

    /**
     * Constructor for deadline class
     * @param description the name of the deadline
     * @param dateString the date or date and time of the deadline
     */
    public Deadline(String description, String dateString) {
        super(description);
        //assume user always input date first, only consider with or without time
        this.dateString = dateString;
        String[] parsed_by = dateString.split("\\s+");
        String deadlineDateFormat = LocalDate.parse(parsed_by[0]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.transformedDate = parsed_by.length > 1 ? deadlineDateFormat + " " + parsed_by[1] : deadlineDateFormat;
    }

    /**
     * The format which the deadline will be printed every time
     * @return the formated string that included names and date or time
     */
    @Override
    public String toString() {
        String first = "[D]" + "[" + this.getStatusIcon() + "] " + this.description + " ";
        String second = "(by: " + this.transformedDate + ")";
        return first + second;
    }

    /**
     * The formatted string to be printed in file
     * @return a formatted string
     */
    @Override
    public String stringInFile() {
        int status = super.isDone ? 1 : 0;
        return "D | " + status + " | " + this.description + " | " + this.dateString;
    }
}
