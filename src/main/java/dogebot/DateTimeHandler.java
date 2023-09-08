package dogebot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DateTimeHandler class turns string input of date and time into a more readable format (e.g. 6/9/2023 1523 to
 * 06 Sep 3:23PM).
 *
 * @author Kenvyn Kwek
 */
public class DateTimeHandler {
    private LocalDateTime date;

    /**
     * Stores date and time from string to LocalDateTime object.
     *
     * @param s date and time.
     */
    public DateTimeHandler(String s) {
        int day;
        int month;
        int year;
        int hour;
        int min;
        String[] temp = s.split(" ");

        if (!s.contains("/")) {
            // if reading from txt file (no '/' as date/time has already been formatted), split according to format
            day = Integer.parseInt(temp[0]);
            month = monthToInt(temp[1]);
            year = Integer.parseInt(temp[2]);
            hour = Integer.parseInt(temp[3].substring(0, 1));

            String amPm = temp[3].substring(temp[3].length() - 2, temp[3].length());
            if (amPm.equals("PM") || amPm.equals("pm")) {
                // intellij DateTimeFormatter uses upper cap "PM"
                // but when .jar is run, DateTimeFormatter uses lower caps "pm" instead
                if (hour + 12 == 24) {
                    hour = 00;
                } else {
                    hour += 12;
                }
            }
            min = Integer.parseInt(temp[3].substring(2, 4));
        } else {
            // date
            String[] dateSplit = temp[0].split("/");
            day = Integer.parseInt(dateSplit[0]);
            month = Integer.parseInt(dateSplit[1]);
            year = Integer.parseInt(dateSplit[2]);
            // time
            hour = Integer.parseInt(temp[1]) / 100;
            min = Integer.parseInt(temp[1]) % 100;
        }
        this.date = LocalDateTime.of(year, month, day, hour, min);
    }

    /**
     * Displays formatted date and time as string.
     *
     * @return formatted date and time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy K:mma");
        String formatDateTime = date.format(formatter);
        return formatDateTime;
    }

    /**
     * Converts MMM format month to integer.
     *
     * @param s MMM format month.
     * @return month as integer.
     */
    public int monthToInt(String s) {
        int month;
        switch (s) {
        case "Jan":
            month = 1;
            break;
        case "Feb":
            month = 2;
            break;
        case "Mar":
            month = 3;
            break;
        case "Apr":
            month = 4;
            break;
        case "May":
            month = 5;
            break;
        case "Jun":
            month = 6;
            break;
        case "Jul":
            month = 7;
            break;
        case "Aug":
            month = 8;
            break;
        case "Sep":
            month = 9;
            break;
        case "Oct":
            month = 10;
            break;
        case "Nov":
            month = 11;
            break;
        case "Dec":
            month = 12;
            break;
        default:
            month = 0;
            break;
        }
        return month;
    }
}
