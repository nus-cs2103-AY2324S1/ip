package task.taskdatetime;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Abstract class encapsulating a date, time component in a Task, parsing input if possible, saving it as a string
 * if not.
 */
public abstract class TaskDateTime implements Serializable {


    /**
     * The output format for all TaskDateTimes, if we managed to parse the date
     */
    public static final String OUTPUT_FORMAT = "MMM dd yyyy HH:mm";

    /**
     * Formatter object the output
     */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(OUTPUT_FORMAT);

    /**
     * Constructor for this object. Default constructor -- Should not be instantiated directly
     */
    public TaskDateTime() {
        // default constructor
    }

    /**
     * Factory method for TaskDateTime, instantiates instance by trying to parse the provided string.
     * Will parse if and only if is in ISO-8601 format, such as
     * 2007-12-03T10:15:30, otherwise will not parse and storage.save entire string as a timing.
     *
     * @param dateTimeAsString the date-time as a string
     * @return the TaskDateTime object that was created with the provided string
     */
    public static TaskDateTime fromDateTimeString(String dateTimeAsString) {
        try {
            // try to make one that we could parse if we can
            return new ParseableTaskDateTime(dateTimeAsString);
        } catch (DateTimeParseException e) {
            // but if we tried to parse, and failed, it's unparseable. Save it as such.
            return new UnparseableTaskDateTime(dateTimeAsString);
        }
    }

    /**
     * Getter method for the dateTime, in string format. Parsed dates will be formatted as per OUTPUT_FORMAT,
     * dates we could not parse will be returned as they were input.
     * @return the dateTime in string format.
     */
    public abstract String getDateTimeAsString();

    /**
     * Subclass of a TaskDateTime, representing a TaskDateTime that holds a parseable date & time
     */
    private static class ParseableTaskDateTime extends TaskDateTime {


        /**
         * the DateTime that we managed to parse
         */
        private LocalDateTime date;


        /**
         * Constructor for a parseable date time. Attempts to parse.
         * @param dateTimeAsString the provided date & time
         * @throws DateTimeParseException if parsing failed
         */
        private ParseableTaskDateTime(String dateTimeAsString) throws DateTimeParseException {

            this.date = LocalDateTime.parse(dateTimeAsString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }

        public String getDateTimeAsString() {
            return this.date.format(TaskDateTime.DATE_TIME_FORMATTER);
        }

    }

    /**
     * String representation, formatted. See TaskDateTime for details.
     * @return the string representation of the date
     */


    private static class UnparseableTaskDateTime extends TaskDateTime {

        /**
         * The entire string provided. Could not parse, so storage.save the whole string instead
         */
        private String dateTimeAsString;

        /**
         * Constructor for this class
         * @param dateTimeAsString the string that we should try to construct with
         */
        private UnparseableTaskDateTime(String dateTimeAsString) {
            this.dateTimeAsString = dateTimeAsString;
        }

        /**
         * The string that was provided to this on instantiation
         * @return the string representation of the date and time provided on instantiation
         */
        public String getDateTimeAsString() {
            return this.dateTimeAsString;
        }




    }
}
