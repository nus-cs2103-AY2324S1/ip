package duke.parse;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeManager {
    public static class DateParseException extends Exception {
        private DateParseException() {}
    }

    /**
     * Transform a String input of datetime to a LocalDateTime instance.
     * The string must be in the format "dd/mm/yyyy hh:mm"
     * @param input the input string
     * @return the LocalDateTime object that corresponds to the input string
     * @throws DateParseException if the input string is in a wrong format
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
     * Parse the string and return a LocalDate instance.
     * @param input the raw string
     * @return the LocalDate instance that corresponds to the input
     * @throws DateParseException when the input string cannot be parsed properly
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
            try {
                return LocalDate.of(
                        Integer.parseInt(dateData[2]),
                        Integer.parseInt(dateData[1]),
                        Integer.parseInt(dateData[0])
                );
            } catch (DateTimeException e) {
                throw new DateParseException();
            }
        } else {
            throw new DateParseException();
        }
    }

    /**
     * Read the given input indicating a time and return a LocalTime instance.
     * @param input the raw input string
     * @return the LocalTime instance that corresponds to the string
     * @throws DateParseException when the string cannot be parsed properly to interpret a time
     */
    public static LocalTime parseTime(String input) throws DateParseException {
        String possibleAmPm = input.substring(input.length() - 2);
        boolean isPm = false;
        boolean isAm = false;
        if (possibleAmPm.equals("am") || possibleAmPm.equals("pm")) {
            if (possibleAmPm.equals("pm")) {
                isPm = true;
            } if (possibleAmPm.equals("am")) {
                isAm = true;
            }
            input = input.substring(0, input.length() - 2);
        }
        String[] timeData = input.split(":");
        if (timeData.length == 1) {
            if (!timeData[0].matches("\\d+")) {
                throw new DateParseException();
            }
            int hour = Integer.parseInt(timeData[0]);
            try {
                return LocalTime.of(
                        (hour == 12 ? (isAm ? 0 : isPm ? 0 : 12) : hour) + (isPm ? 12 : 0),
                        0
                );
            } catch (DateTimeException e) {
                throw new DateParseException();
            }
        } else if (timeData.length == 2) {
            for (String data : timeData) {
                if (!data.matches("\\d+")) {
                    throw new DateParseException();
                }
            }
            int hour = Integer.parseInt(timeData[0]);
            try {
                return LocalTime.of(
                        (hour == 12 ? (isAm ? 0 : isPm ? 0 : 12) : hour) + (isPm ? 12 : 0),
                        Integer.parseInt(timeData[1])
                );
            } catch (DateTimeException e) {
                throw new DateParseException();
            }
        } else {
            throw new DateParseException();
        }
    }

    public static String dateToStringData(LocalDateTime dateTime) {
        return DateTimeManager.dateToStringData(dateTime.toLocalDate()) + " "
                + DateTimeManager.timeDataFrom(dateTime.toLocalTime());
    }

    public static String dateToStringData(LocalDate date) {
        return date.getDayOfMonth() + "/"
                + date.getMonthValue() + "/"
                + date.getYear();
    }

    public static String timeDataFrom(LocalTime time) {
        return time.getHour() + ":" + time.getMinute();
    }

    public static String dateToDisplay(LocalDateTime dateTime) {
        return DateTimeManager.dateToDisplay(dateTime.toLocalDate()) + " "
                + DateTimeManager.timeDisplayFrom(dateTime.toLocalTime());
    }

    public static String dateToDisplay(LocalDate date) {
        if (date.equals(LocalDate.now())) {
            return "today";
        } else if (date.equals(LocalDate.now().plusDays(1))) {
            return "tomorrow";
        } else {
            return dateToStringData(date);
        }
    }

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
