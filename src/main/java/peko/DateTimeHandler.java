package peko;

import java.time.LocalDateTime;

public class DateTimeHandler {
    private LocalDateTime date;
    private String dateTimeString;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int min;

    /**
     * Constructs a DateTimeHandler object from a string representation of date and time.
     * This constructor takes a string containing date and time information, parses it,
     * and initializes the DateTimeHandler object with the parsed values.
     *
     * @param s The string representation of date and time in the format "dd/mm/yyyy HHmm".
     */
    public DateTimeHandler(String s) {

        String[] dateTime = s.split(" ");
        String[] dateString = dateTime[0].split("/");
        int timeString = Integer.parseInt(dateTime[1]);
        day = Integer.parseInt(dateString[0]);
        month = Integer.parseInt(dateString[1]);
        year = Integer.parseInt(dateString[2]);
        hour = timeString/100;
        min = timeString%100;
        date = LocalDateTime.of(year,month,day,hour,min);
        dateTimeString = date.getMonth() +  " " + date.getDayOfMonth() + " " + date.getYear();
    }


    /**
     * Generates a formatted string representation of date and time.
     * This method creates and returns a string containing the date, time, and optionally padded minutes.
     *
     * @return A formatted string representation of date and time.
     */
    public String stringDisplay() {
        return dateTimeString + " " + hour + ":" + ((min < 10) ? "0" + min : min);
    }

    /**
     * Returns a string representation of the date and time.
     * This method generates and returns a string containing the formatted date and time,
     * with day, month, year, hour, and optional padded minutes.
     *
     * @return A string representation of the date and time.
     */
    @Override
    public String toString() {
        return day + "/" + month + "/" + year + " " + hour + ((min < 10) ? "0" + min : min);
    }
}
