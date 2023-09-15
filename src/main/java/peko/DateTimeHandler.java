package peko;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DateTimeHandler {
    private LocalDateTime date;
    private String dateTimeString;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int min;
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
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMMM dd yyyy HH:mm");
        dateTimeString = date.format(format);
    }

    public LocalDateTime getDate() {
        return date;
    }
    public String stringDisplay() {
        return dateTimeString;

        //return dateTimeString + " " + hour + ":" + ((min < 10) ? "0" + min : min);
    }
    public String toString() {
        return day + "/" + month + "/" + year + " " + hour + ((min < 10) ? "0" + min : min);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DateTimeHandler) {
            return date.equals(((DateTimeHandler) o).date);
        }
        return false;
    }
}
