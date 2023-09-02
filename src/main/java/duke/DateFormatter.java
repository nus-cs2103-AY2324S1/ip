package duke;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;
public class DateFormatter {
    public LocalDateTime stringToDate(String date) throws DateTimeException {
        String dateFormat = "yyyy-MM-dd HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        return dateTime;
    }

    public String dateToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String result = dateTime.format(formatter);
        return result;
    }

    public String saveDateToFile(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String result = dateTime.format(formatter);
        return result;
    }

    public String dateTimeToDate(LocalDateTime dateTime) {
        LocalDate date = dateTime.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String result = date.format(formatter);
        return result;
    }

    public String dateTimeToTime(LocalDateTime dateTime) {
        LocalTime time = dateTime.toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String result = time.format(formatter);
        return result;
    }

}
