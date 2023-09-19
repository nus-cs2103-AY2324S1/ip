package gman;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline Task that contains a description and a deadline.
 */
public class Deadline extends Task{

    protected String by;
    private final LocalDate deadline;
    protected DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    /**
     * A Deadline task constructor.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.deadline = LocalDate.parse(by, inputFormat);
        this.by = by;
    }


    /**
     * Returns A string representation of the Deadline Task, including the symbol, status icon,
     * description and deadline.
     *
     * @return a String to be printed to the user.
     */
    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + description + " (by: " + deadline.format(outputFormat) + ")";
    }


    /**
     * Reads a line in a .txt file that represents a Deadline task and converts it to a Deadline Task.
     *
     * @param segments A string array that is split by " | ", separating the line into useful chunks.
     * @return A deadline task.
     */
    public static Deadline readFromFile(String[] segments){
        String symbol = segments[1];
        String description = segments[2];
        String by = segments[3];
        Deadline toReturn =  new Deadline(description, by);
        if (symbol.equals("X")) {
            toReturn.mark();
        }
        return toReturn;
    }


    /**
     * Returns A String to be written into the .txt file.
     *
     * @return A formatted String for the Deadline task with " | " separators.
     */
    @Override
    public String stringToWrite() {
        return "D" + " | " + super.stringToWrite() + " | " + by;
    }


}
