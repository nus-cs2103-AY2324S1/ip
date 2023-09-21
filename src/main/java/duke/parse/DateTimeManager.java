package duke.parse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Contains methods to deal with datetime,
 * including methods to convert java date and time to readable string and string for storage,
 * and vice versa.
 */
public class DateTimeManager {
    /**
     * Thrown when the date string given by the user cannot be parsed properly.
     */
    public static class DateParseException extends Exception {
        private DateParseException() {}
    }

    /**
     * Transforms a String input of datetime to a LocalDateTime instance.
     * The string must be in the format "dd/mm/yyyy hh:mm".
     * Date part may be replaced by "today", "tmr" or "tomorrow".
     * Time part may have indicator "am" or "pm".
     * @param input The input string.
     * @return The LocalDateTime object that corresponds to the input string.
     * @throws DateParseException If the input string is in a wrong format.
     */
    public static LocalDateTime inputToDate(String input) throws DateParseException {
        String[] splitBySpace = input.split(" ");
        if (splitBySpace.length != 2) {
            throw new DateParseException();
        }

        LocalDate date = DateTimeManager.parseDate(splitBySpace[0]);
        LocalTime time = DateTimeManager.parseTime(splitBySpace[1]);

        return LocalDateTime.of(date, time);
    }

    /**
     * Parses the string and return a LocalDate instance.
     * @param input The raw string.
     * @return The LocalDate instance that corresponds to the input.
     * @throws DateParseException When the input string cannot be parsed properly.
     */
    public static LocalDate parseDate(String input) throws DateParseException {
        String[] dateData = input.split("/");
        if (dateData.length == 1) {
            switch (dateData[0]) {
            case "today":
                return LocalDate.now();
            case "tomorrow":
            case "tmr":
                return LocalDate.now().plusDays(1);
            default:
                throw new DateParseException();
            }
        } else if (dateData.length == 3) {
            for (String data : dateData) {
                if (data.matches("0+") || !data.matches("\\d+")) {
                    throw new DateParseException();
                }
            }
            return LocalDate.of(
                    Integer.parseInt(dateData[2]),
                    Integer.parseInt(dateData[1]),
                    Integer.parseInt(dateData[0])
            );
        } else {
            throw new DateParseException();
        }
    }

    /**
     * Reads the given input indicating a time and return a LocalTime instance.
     * @param input The raw input string.
     * @return The LocalTime instance that corresponds to the string.
     * @throws DateParseException When the string cannot be parsed properly to interpret a time.
     */
    public static LocalTime parseTime(String input) throws DateParseException {
        String possibleAmPm = input.substring(input.length() - 2);
        boolean isPm = possibleAmPm.equals("pm");
        boolean isAm = possibleAmPm.equals("am");
        if (isAm || isPm) {
            input = input.substring(0, input.length() - 2);
        }
        String[] timeData = input.split(":");
        if (timeData.length == 1) {
            if (!timeData[0].matches("\\d+")) {
                throw new DateParseException();
            }
            int hour = Integer.parseInt(timeData[0]);
            return LocalTime.of((hour == 12 ? (isAm ? 0 : isPm ? 0 : 12) : hour) + (isPm ? 12 : 0), 0);
        } else if (timeData.length == 2) {
            for (String data : timeData) {
                if (!data.matches("\\d+")) {
                    throw new DateParseException();
                }
            }
            int hour = Integer.parseInt(timeData[0]);
            return LocalTime.of((hour == 12 ? (isAm ? 0 : isPm ? 0 : 12) : hour) + (isPm ? 12 : 0),
                    Integer.parseInt(timeData[1])
            );
        } else {
            throw new DateParseException();
        }
    }

    /**
     * Converts the given datetime to string for storage
     * @param dateTime The instance of LocalDateTime to convert.
     * @return The string containing the data of the datetime.
     */
    public static String dateToStringData(LocalDateTime dateTime) {
        return DateTimeManager.dateToStringData(dateTime.toLocalDate()) + " "
                + DateTimeManager.timeDataFrom(dateTime.toLocalTime());
    }

    /**
     * Converts the given date to string data for storage.
     * @param date The instance of LocalDate to convert.
     * @return The string containing data of the date.
     */
    public static String dateToStringData(LocalDate date) {
        return date.getDayOfMonth() + "/"
                + date.getMonthValue() + "/"
                + date.getYear();
    }

    /**
     * Converts the given time to string data for storage.
     * @param time The instance of LocalTime to convert.
     * @return The string containing the data of the time.
     */
    public static String timeDataFrom(LocalTime time) {
        return time.getHour() + ":" + time.getMinute();
    }

    /**
     * Converts a datetime to readable form, for display in UI.
     * @param dateTime The instance of LocalDateTime to display.
     * @return The string representing the datetime to be displayed.
     */
    public static String dateToDisplay(LocalDateTime dateTime) {
        return DateTimeManager.dateToDisplay(dateTime.toLocalDate()) + " "
                + DateTimeManager.timeDisplayFrom(dateTime.toLocalTime());
    }

    /**
     * Converts the date given to readable form, for display in UI.
     * @param date The instance of LocalDate to display.
     * @return The string representing the date to be displayed.
     */
    public static String dateToDisplay(LocalDate date) {
        if (date.equals(LocalDate.now())) {
            return "today";
        } else if (date.equals(LocalDate.now().plusDays(1))) {
            return "tomorrow";
        } else {
            return dateToStringData(date);
        }
    }

    /**
     * Converts the time to readable form, for display in UI.
     * @param time The instance of LocalTime to convert.
     * @return The string representing the time to be displayed.
     */
    public static String timeDisplayFrom(LocalTime time) {
        if (time.getHour() < 12) {
            return (time.getHour() == 0
                        ? "12"
                        : DateTimeManager.twoDecimalPlaces(time.getHour())
                    ) + ":" + DateTimeManager.twoDecimalPlaces(time.getMinute()) + "am";
        } else {
            return (time.getHour() == 12
                        ? "12"
                        : DateTimeManager.twoDecimalPlaces(time.getHour() - 12)
                    ) + ":" + DateTimeManager.twoDecimalPlaces(time.getMinute()) + "pm";
        }
    }

    private static String twoDecimalPlaces(int number) {
        return number < 10 ? "0" + number : Integer.toString(number);
    }
}
