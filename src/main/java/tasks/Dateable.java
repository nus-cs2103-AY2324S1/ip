package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Dateable object that can either be a DotDateTime which encapsulates
 * a LocalDateTime object, or a TimeDescription which stores the description
 * of the date-time as a String.
 * <p>
 * Dateable also provides support for WhatsgoingonCommand through its
 * comparison methods.
 */
public abstract class Dateable {

    /**
     * Checks if stored date is before or on given date
     * TimeDescription must return false for this method.
     *
     * @param date from user input.
     * @return true if before or on, else false.
     */
    public abstract boolean isBeforeOrOn(LocalDateTime date);

    /**
     * Checks if stored date is after or on given date
     * TimeDescription must return false for this method.
     *
     * @param date from user input.
     * @return true if after or on, else false.
     */
    public abstract boolean isAfterOrOn(LocalDateTime date);

    /**
     * DotDateTime is one of the subclasses of Dateable,
     * which stores a LocalDateTime.
     */
    public static class DotDateTime extends Dateable {
        private final LocalDateTime dotDateTime;

        DotDateTime(String input) {
            this.dotDateTime = LocalDateTime.parse(input);
        }

        @Override
        public boolean isBeforeOrOn(LocalDateTime date) {
            return this.dotDateTime.isBefore(date) || this.dotDateTime.isEqual(date);
        }

        @Override
        public boolean isAfterOrOn(LocalDateTime date) {
            return this.dotDateTime.isAfter(date) || this.dotDateTime.isEqual(date);
        }

        @Override
        public String toString() {
            return this.dotDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy ha"));
        }
    }

    /**
     * TimeDescription is one of the subclasses of Dateable,
     * which stores a String describing the date-time.
     */
    public static class TimeDescription extends Dateable {
        private final String timeDescription;

        TimeDescription(String input) {
            this.timeDescription = input;
        }

        @Override
        public boolean isBeforeOrOn(LocalDateTime date) {
            return false;
        }

        @Override
        public boolean isAfterOrOn(LocalDateTime date) {
            return false;
        }

        @Override
        public String toString() {
            return this.timeDescription;
        }
    }

    /**
     * Validates that input is of the correct date-time input format accepted,
     *  and then returns the appropriate Dateable object.
     *  This is the factory method for Dateable.
     *
     * @param input This is the user input.
     * @return A DotDateTime if input is of format, else a TimeDescription.
     */
    public static Dateable of(String input) {
        //(Only) regex inspired by ChatGPT
        //Prompt: Give me a regex crash course
        //We allow tutorial's format e.g. 2/12/2019 1800
        //We need to parse it into yyyy-MM-ddTHH:mm:ss
        String regex = "^([1-9]|0[1-9]|[1-2][0-9]|3[0-1])/([1-9]|0[1-9]|1[0-2])/[0-9]{4} "
                + "([0-1][0-9]|2[0-3])([0-5][0-9])$";
        if (input.matches(regex)) {
            // Parse into format accepted by LocalDateTime
            String[] splitBySpace = input.split(" ");
            String[] dayMonthYear = splitBySpace[0].split("/");
            String hour = splitBySpace[1].substring(0, 2);
            String minute = splitBySpace[1].substring(2);
            return new DotDateTime(String.format("%s-%02d-%02dT%s:%s:00", dayMonthYear[2],
                    Integer.valueOf(dayMonthYear[1]), Integer.valueOf(dayMonthYear[0]), hour, minute));
        } else {
            return new TimeDescription(input);
        }
    }
}
