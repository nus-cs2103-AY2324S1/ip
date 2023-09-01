package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a custom date-time utility to handle and format date and time values.
 */
public class DateTime {
    /** Formatter for the date representation. */
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    /** Formatter for the time representation. */
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    /** The local date object. */
    LocalDate ld;

    /** The local time object. */
    LocalTime lt;

    /**
     * Initializes a DateTime object based on the given string representation.
     *
     * @param dt The string representation of date-time to parse.
     * @throws DateTimeParseException If the input string cannot be parsed.
     */
    public DateTime(String dt) throws DateTimeParseException {
        String[] splt = dt.split(" ");

        if (splt.length == 1) {
            this.ld = LocalDate.parse(splt[0], dateFormatter);
        } else if (splt.length == 2) {
            this.ld = LocalDate.parse(splt[0], dateFormatter);
            this.lt = LocalTime.parse(splt[1], timeFormatter);
        } else {
            throw new DateTimeParseException(
                    "Sorry I cannot recognise your date and time!"
                            + " Please follow the format: yyyy/M/d HHmm", dt, 0);
        }
    }

    /**
     * Returns the formatted date string.
     *
     * @return The formatted date string.
     */
    public String getFormattedDate() {
        return this.ld.format(DateTimeFormatter.ofPattern("MM-d-yyyy"));
    }

    /**
     * Returns the formatted time string or null if there's no time component.
     *
     * @return The formatted time string or null.
     */
    public String getFormattedTime() {
        if (this.lt != null) {
            return this.lt.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return null;
    }

    /**
     * Returns the string representation of the date-time object.
     *
     * @return The formatted string of date-time.
     */
    public String toString(){
        return getFormattedDate() +
                ((this.lt == null) ? "" : " " + getFormattedTime());
    }
}
