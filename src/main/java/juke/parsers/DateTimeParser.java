package juke.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import juke.commons.classes.JukeObject;
import juke.commons.exceptions.parsers.JukeDateFormatParseException;

/**
 * Parses Datetime Strings into the relevant {@code LocalDateTime} objects.
 *
 * Note: The regex patterns are adapted from ChatGPT, modified to better meet the requirements of
 * Juke. <a href="https://regex101.com/">This</a> was used to build and test the new regex patterns.
 */
public class DateTimeParser extends JukeObject {
    //@@author asdfghjkxd-reused
    // Regex strings are reused with some modification from ChatGPT, and is also built and tested with
    // https://regex101.com/.
    /**
     * Regex for recognising DateTime inputs. The regex pattern below are adapted from ChatGPT,
     * modified to better meet the requirements of Juke.
     * <p>
     * <a href="https://regex101.com/">This</a> was used to build and test the new regex patterns.
     */
    private static final String DATETIME_REGEX = "^(0?[1-9]|[12][0-9]|3[01])(\\/|-)(0?[1-9]|1[0-2])(\\/|-)\\d{4} "
            + "([01]?[0-9]|2[0-3])?(-|:)?[0-5][0-9]$";

    /**
     * Regex for recognising Date inputs. The regex pattern below are adapted from ChatGPT,
     * modified to better meet the requirements of Juke.
     * <p>
     * <a href="https://regex101.com/">This</a> was used to build and test the new regex patterns.
     */
    private static final String DATE_REGEX = "^(0?[1-9]|[12][0-9]|3[01])(\\/|-)(0?[1-9]|1[0-2])(\\/|-)\\d{4}";
    //@@ author

    /**
     * Checks if the input date is of the correct Date format. A Date format is specified by only the date
     * and does not include the time.
     *
     * @param date Input date
     * @return true if the String is a valid Date format, else false
     */
    public static boolean isValidDate(String date) {
        return Pattern.matches(DateTimeParser.DATE_REGEX, date);
    }

    /**
     * Checks if the input date is of the correct DateTime format. A DateTime format specifies both the date
     * and the time.
     *
     * @param datetime Input datetime
     * @return true if the String is a valid DateTime format, else false
     */
    public static boolean isValidDateTime(String datetime) {
        return Pattern.matches(DateTimeParser.DATETIME_REGEX, datetime);
    }

    /**
     * Parses the input String into a {@code LocalDateTime} object, provided that the
     * string meets the requirement of either being a valid Date or DateTime.
     *
     * @param datetime Input date/datetime string
     * @return {@code LocalDateTime} object representing the input datetime
     * @throws JukeDateFormatParseException if the input String is not in the valid Date or DateTime format as
     *     described above
     */
    public static LocalDateTime parse(String datetime) {
        try {
            // strip the datetime first to avoid any errors
            datetime = datetime.strip();

            if (isValidDateTime(datetime)) {
                //@@author asdfghjkxd-reused
                // Regex strings are reused with major modification from ChatGPT, and is built and tested with
                // https://regex101.com/.
                String[] dateTimeSplit = datetime.split(" ");
                String[] parsedDate = dateTimeSplit[0].split("\\/|-");
                boolean isMatchingRegex = Pattern.matches("\\d{4}", dateTimeSplit[1]);
                //@@author

                String[] parsedTime = isMatchingRegex
                        ? new String[]{dateTimeSplit[1].substring(0, 2), dateTimeSplit[1].substring(2, 4)}
                        : dateTimeSplit[1].split("-|:");
                return LocalDateTime.parse(parsedDate[2] + "-" + parsedDate[1] + "-" + parsedDate[0] + "T"
                                                   + parsedTime[0] + ":" + parsedTime[1] + ":00");
            } else if (isValidDate(datetime)) {
                String[] date = datetime.split("\\/|-");
                return LocalDateTime.parse(date[2] + "-" + date[1] + "-" + date[0] + "T00:00:00");
            }
        } catch (DateTimeParseException ex) {
            throw new JukeDateFormatParseException(
                    "Oh no! I cannot understand the date format you have given me! Ensure "
                            + "that datetimes are given as such: DD(/-)MM(/-)YYYY[ "
                            + "HH(:-)MM], where (...) represents the set of acceptable "
                            + "symbols and [...] represents optional arguments.");
        }

        throw new JukeDateFormatParseException(
                "Oh no! I cannot understand the date format you have given me!Ensure "
                        + "that datetimes are given as such:\nDD(/-)MM(/-)YYYY[ "
                        + "HH(:-)MM], where (...) represents the set of acceptable "
                        + "symbols and [...] represents optional arguments.");
    }

    /**
     * Parses stored datafile representation of {@code LocalDateTime}
     * objects into actual {@code LocalDateTime} objects.
     *
     * @param dateTimeString Stored string
     * @return {@code LocalDateTime} object
     */
    public static LocalDateTime fromDateTimeString(String dateTimeString) {
        try {
            return LocalDateTime.parse(dateTimeString);
        } catch (DateTimeParseException | JukeDateFormatParseException ex) {
            throw new JukeDateFormatParseException("Oh no! I cannot understand the date format you have given me!"
                                                           + "\nEnsure that datetimes are given as such: "
                                                           + "YYYY-MM-DDTHH:MM");
        }
    }
}
