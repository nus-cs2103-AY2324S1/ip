import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeManager {
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

        String[] dateData = splitBySpace[0].split("/");
        LocalDate date;
        if (dateData.length == 1) {
            switch (dateData[0]) {
                case "today":
                    date = LocalDate.now();
                    break;
                case "tomorrow":
                case "tmr":
                    date = LocalDate.now().plusDays(1);
                    break;
                default:
                    throw new DateParseException();
            }
        } else if (dateData.length == 3) {
            for (String data : dateData) {
                if (data.matches("0+") || !data.matches("\\d+")) {
                    throw new DateParseException();
                }
            }
            date = LocalDate.of(
                    Integer.parseInt(dateData[2]),
                    Integer.parseInt(dateData[1]),
                    Integer.parseInt(dateData[0])
            );
        } else {
            throw new DateParseException();
        }

        String possibleAmPm = splitBySpace[1].substring(splitBySpace[1].length() - 2);
        boolean isPm = false;
        if (possibleAmPm.equals("am") || possibleAmPm.equals("pm")) {
            if (possibleAmPm.equals("pm")) {
                isPm = true;
            }
            splitBySpace[1] = splitBySpace[1].substring(0, splitBySpace[1].length() - 2);
        }
        String[] timeData = splitBySpace[1].split(":");
        LocalTime time;
        if (timeData.length == 1) {
            if (!timeData[0].matches("\\d+")) {
                throw new DateParseException();
            }
            time = LocalTime.of(
                    Integer.parseInt(timeData[0]) + (isPm ? 12 : 0),
                    0
            );
        } else if (timeData.length == 2) {
            for (String data : timeData) {
                if (!data.matches("\\d+")) {
                    throw new DateParseException();
                }
            }
            time = LocalTime.of(
                    Integer.parseInt(timeData[0]) + (isPm ? 12 : 0),
                    Integer.parseInt(timeData[1])
            );
        } else {
            throw new DateParseException();
        }

        return LocalDateTime.of(date, time);
    }

    public static String dateToStringData(LocalDateTime dateTime) {
        return dateTime.getDayOfMonth() + "/"
                + dateTime.getMonthValue() + "/"
                + dateTime.getYear() + " "
                + DateTimeManager.timeDisplayFrom(dateTime);
    }

    public static String dateToDisplay(LocalDateTime dateTime) {
        if (dateTime.toLocalDate().equals(LocalDate.now())) {
            return "today " + DateTimeManager.timeDisplayFrom(dateTime);
        } else if (dateTime.toLocalDate().equals(LocalDate.now().plusDays(1))) {
            return "tomorrow " + DateTimeManager.timeDisplayFrom(dateTime);
        } else {
            return dateToStringData(dateTime);
        }
    }

    private static String timeDisplayFrom(LocalDateTime dateTime) {
        if (dateTime.getHour() < 12) {
            return (dateTime.getHour() == 0
                        ? "12"
                        : DateTimeManager.twoDecimalPlaces(dateTime.getHour())
                    ) + ":" + DateTimeManager.twoDecimalPlaces(dateTime.getMinute()) + "am";
        } else {
            return (dateTime.getHour() == 12
                        ? "12"
                        : DateTimeManager.twoDecimalPlaces(dateTime.getHour() - 12)
                    ) + ":" + DateTimeManager.twoDecimalPlaces(dateTime.getMinute()) + "pm";
        }
    }

    private static String twoDecimalPlaces(int number) {
        return number < 10 ? "0" + number : Integer.toString(number);
    }
}
