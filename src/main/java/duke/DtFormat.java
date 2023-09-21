package duke;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

/**
 * Manages DateTimeFormatters to be used in the application.
 */

public class DtFormat {
    private ArrayList<DateTimeFormatter> formatters;
    private DateTimeFormatter outDateFormat;

    /**
     * Constructor for the DtFormat class, creating severaL default DateTimeFormatters to
     * be used.
     */
    public DtFormat() {
        formatters = new ArrayList<>();
        DateTimeFormatter f1 = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-M-d[ H:m]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();

        DateTimeFormatter f2 = new DateTimeFormatterBuilder()
                .appendPattern("d/M/yyyy[ Hmm]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();


        formatters.add(f1);
        formatters.add(f2);
        formatters.add(DateTimeFormatter.ofPattern("yyyy-M-d"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy Hmm"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy H"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy"));
        outDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
    }

    /**
     * Returns an ArrayList of DateTimeFormatters to recognize user input.
     *
     * @returns ArrayList of DateTimeFormatters to recognize user input
     */

    public ArrayList<DateTimeFormatter> getFormatters() {
        return formatters;
    }
    /**
     * Adds a datetime format to the list of recognized formats.
     */
    public void addReadFormat(String k) {
        formatters.add(DateTimeFormatter.ofPattern(k));
    }
    /**
     * Removes a datetime format by index from the list of recognized formats.
     */
    public void removeReadFormat(int x) {
        formatters.remove(x);
    }
    /**
     * Returns a DateTimeFormatter to format output.
     *
     * @returns DateTimeFormatter to format output
     */
    public DateTimeFormatter getOutFormatter() {
        return outDateFormat;
    }
    /**
     * Sets the output DateTimeFormatter to the given datetime format.
     */
    public void setOutFormatter(String k) {
        outDateFormat = DateTimeFormatter.ofPattern(k);
    }
}
