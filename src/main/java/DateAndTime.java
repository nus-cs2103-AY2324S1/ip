import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.MonthDay;

/**
 * Manages the date and time based on a user input.
 */
public class DateAndTime {

    /**
     * Parses a given string date. For dates only.
     * @param date The date in YYYY-MM-DD format
     * @return The formatted date string
     */
    public String dayParse(String date) {
        int year = LocalDate.parse(date).getYear();

        String month = LocalDate.parse(date).getMonth().toString();

        int day = LocalDate.parse(date).getDayOfMonth();

        return month + " " + day + ", " + year;
    }

    /**
     * Formats the date and time
     * @param date The date in YYYY-MM-DD format.
     * @param timing The time in HH:MM format
     * @return The formatted date and time String.
     */
    public String dayParse(String date, String timing) {
        int year = LocalDate.parse(date).getYear();

        String month = LocalDate.parse(date).getMonth().toString();

        int day = LocalDate.parse(date).getDayOfMonth();

        String time = LocalTime.parse(timing).toString();

        return month + " " + day + ", " + year + ", " + time;
    }

    /**
     * Checks if a given start and end date are valid.
     * @param start Start date
     * @param end End date
     * @return If the given date range is valid.
     */
    public boolean isValidDate(String start, String end) {
        return !LocalDate.parse(start).isAfter(LocalDate.parse(end));
    }

    /**
     * Checks if the given start and end date and times are valid.
     * @param start Start date
     * @param startTime Start time
     * @param end End date
     * @param endTime End time
     * @return If the Starting and Ending date and times are valid.
     */
    public boolean isValidDate(String start, String startTime, String end, String endTime) {
        return LocalDate.parse(start).isBefore(LocalDate.parse(end))
            ? true
            : LocalDate.parse(start).isEqual(LocalDate.parse(end)) && (LocalTime.parse(startTime)
                .isBefore(LocalTime.parse(endTime)) || LocalTime.parse(startTime).equals(LocalTime.parse(endTime)));
    }
}
